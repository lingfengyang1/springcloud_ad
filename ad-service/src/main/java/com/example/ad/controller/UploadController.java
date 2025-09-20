package com.example.ad.controller;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class UploadController {

    @Autowired
    private FastFileStorageClient storageClient;

    // 从配置文件中读取上传路径
    @Value("${file.upload.base-path}")
    private String baseUploadPath;

    // 从配置文件中读取允许的文件类型
    @Value("${file.upload.allowed-types}")
    private String[] allowedFileTypes;

    /**
     * 文件上传接口 - 保存到服务器本地文件系统
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFileToServer(@RequestParam("file") MultipartFile file) {
        // 验证文件是否为空
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("上传文件不能为空");
        }

        try {
            // 1. 获取文件信息
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || !originalFilename.contains(".")) {
                return ResponseEntity.badRequest().body("文件名格式不正确");
            }

            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();

            // 验证文件类型
            if (!isFileTypeAllowed(extName)) {
                return ResponseEntity.badRequest().body("不支持的文件类型: " + extName);
            }

            // 2. 生成唯一文件名，避免重复
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String fileName = uuid + "." + extName;

            // 3. 按日期创建目录，便于文件管理
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
            String dateDir = sdf.format(new Date());
            File uploadDir = new File(baseUploadPath + dateDir);

            // 4. 确保目录存在
            if (!uploadDir.exists() && !uploadDir.mkdirs()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("无法创建上传目录");
            }

            // 5. 创建目标文件
            File destFile = new File(uploadDir, fileName);

            // 6. 保存文件
            file.transferTo(destFile);

            // 7. 返回访问路径（实际项目中可配置域名+路径）
            String accessPath = "/upload/files/" + dateDir + fileName;
            return ResponseEntity.ok(accessPath);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("文件上传失败：" + e.getMessage());
        }
    }

    /**
     * 验证文件类型是否允许上传
     */
    private boolean isFileTypeAllowed(String fileType) {
        if (allowedFileTypes == null || allowedFileTypes.length == 0) {
            return true; // 如果未配置，则允许所有类型
        }

        for (String type : allowedFileTypes) {
            if (type.equalsIgnoreCase(fileType)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 文件上传接口
     */
    @PostMapping("/upload2")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        // 验证文件是否为空
        if (file.isEmpty()) {
            throw new RuntimeException("上传文件不能为空");
        }

        // 获取文件扩展名
        String originalFilename = file.getOriginalFilename();
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        
        // 上传文件到FastDFS
        StorePath storePath = storageClient.uploadFile(
                file.getInputStream(),
                file.getSize(),
                extName,
                null
        );
        
        // 返回完整的文件路径
        return storePath.getFullPath();
    }
}

package com.example.ad.config; // 包路径需和你的项目一致

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 标记为配置类，Spring 启动时会加载
public class WebMvcResourceConfig implements WebMvcConfigurer {

    // 从 YAML 配置中注入本地上传根路径（和你上传接口的 base-path 一致）
    @Value("${file.upload.base-path}")
    private String localUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 核心映射规则：
        // - 左半部分：HTTP 访问路径（需和你上传接口返回的 accessPath 前缀一致）
        // - 右半部分：本地文件路径（必须加 "file:" 前缀，表示访问本地文件系统）
        registry.addResourceHandler("/upload/files/**")
                .addResourceLocations("file:" + localUploadPath);
    }
}
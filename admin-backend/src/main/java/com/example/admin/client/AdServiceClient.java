package com.example.admin.client;

import com.example.admin.entity.Ad;
import com.example.admin.entity.AdCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@FeignClient(name = "ad-service")
public interface AdServiceClient {

    // 广告相关接口
    @GetMapping("/ads")
    List<Ad> findAllAds();

    @GetMapping("/ads/{id}")
    Ad findAdById(@PathVariable("id") Long id);

    @GetMapping("/ads/category/{categoryId}")
    List<Ad> findAdsByCategoryId(@PathVariable("categoryId") Long categoryId);

    @PostMapping("/ads")
    int insertAd(@RequestBody Ad ad);

    @PutMapping("/ads")
    int updateAd(@RequestBody Ad ad);

    @DeleteMapping("/ads/{id}")
    int deleteAd(@PathVariable("id") Long id);

    // 分类相关接口
    @GetMapping("/categories")
    List<AdCategory> findAllCategories();

    @GetMapping("/categories/{id}")
    AdCategory findCategoryById(@PathVariable("id") Long id);

    // 文件上传接口
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile file);
}

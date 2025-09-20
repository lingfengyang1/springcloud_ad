package com.example.admin.service;

import com.example.admin.entity.Ad;
import com.example.admin.entity.AdCategory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdManageService {
    // 广告相关方法
    List<Ad> findAllAds();
    
    Ad findAdById(Long id);
    
    List<Ad> findAdsByCategoryId(Long categoryId);
    
    int saveAd(Ad ad);
    
    int deleteAd(Long id);
    
    // 分类相关方法
    List<AdCategory> findAllCategories();
    
    // 文件上传方法
    String uploadFile(MultipartFile file) throws IOException;
}

package com.example.admin.service.impl;

import com.example.admin.client.AdServiceClient;
import com.example.admin.entity.Ad;
import com.example.admin.entity.AdCategory;
import com.example.admin.service.AdManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AdManageServiceImpl implements AdManageService {

    @Autowired
    private AdServiceClient adServiceClient;

    @Override
    public List<Ad> findAllAds() {
        return adServiceClient.findAllAds();
    }

    @Override
    public Ad findAdById(Long id) {
        return adServiceClient.findAdById(id);
    }

    @Override
    public List<Ad> findAdsByCategoryId(Long categoryId) {
        return adServiceClient.findAdsByCategoryId(categoryId);
    }

    @Override
    public int saveAd(Ad ad) {
        if (ad.getId() == null) {
            return adServiceClient.insertAd(ad);
        } else {
            return adServiceClient.updateAd(ad);
        }
    }

    @Override
    public int deleteAd(Long id) {
        return adServiceClient.deleteAd(id);
    }

    @Override
    public List<AdCategory> findAllCategories() {
        return adServiceClient.findAllCategories();
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        return adServiceClient.uploadFile(file);
    }
}

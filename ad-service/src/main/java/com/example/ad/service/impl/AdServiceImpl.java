package com.example.ad.service.impl;

import com.example.ad.entity.Ad;
import com.example.ad.mapper.AdMapper;
import com.example.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private AdMapper adMapper;

    @Override
    public List<Ad> findAll() {
        return adMapper.findAll();
    }

    @Override
    public Ad findById(Long id) {
        return adMapper.findById(id);
    }

    @Override
    public List<Ad> findByCategoryId(Long categoryId) {
        return adMapper.findByCategoryId(categoryId);
    }

    @Override
    public int insert(Ad ad) {
        return adMapper.insert(ad);
    }

    @Override
    public int update(Ad ad) {
        return adMapper.update(ad);
    }

    @Override
    public int delete(Long id) {
        return adMapper.delete(id);
    }
}

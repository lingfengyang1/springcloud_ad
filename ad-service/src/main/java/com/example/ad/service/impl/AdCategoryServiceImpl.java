package com.example.ad.service.impl;

import com.example.ad.entity.AdCategory;
import com.example.ad.mapper.AdCategoryMapper;
import com.example.ad.service.AdCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdCategoryServiceImpl implements AdCategoryService {

    @Autowired
    private AdCategoryMapper categoryMapper;

    @Override
    public List<AdCategory> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public AdCategory findById(Long id) {
        return categoryMapper.findById(id);
    }

    @Override
    public int insert(AdCategory category) {
        return categoryMapper.insert(category);
    }

    @Override
    public int update(AdCategory category) {
        return categoryMapper.update(category);
    }

    @Override
    public int delete(Long id) {
        return categoryMapper.delete(id);
    }
}

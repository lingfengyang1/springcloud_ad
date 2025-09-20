package com.example.ad.service;

import com.example.ad.entity.Ad;
import java.util.List;

public interface AdService {
    /**
     * 查询所有广告
     */
    List<Ad> findAll();
    
    /**
     * 根据ID查询广告
     */
    Ad findById(Long id);
    
    /**
     * 根据分类ID查询广告
     */
    List<Ad> findByCategoryId(Long categoryId);
    
    /**
     * 新增广告
     */
    int insert(Ad ad);
    
    /**
     * 更新广告
     */
    int update(Ad ad);
    
    /**
     * 删除广告
     */
    int delete(Long id);
}

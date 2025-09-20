package com.example.ad.service;

import com.example.ad.entity.AdCategory;
import java.util.List;

public interface AdCategoryService {
    /**
     * 查询所有分类
     */
    List<AdCategory> findAll();
    
    /**
     * 根据ID查询分类
     */
    AdCategory findById(Long id);
    
    /**
     * 新增分类
     */
    int insert(AdCategory category);
    
    /**
     * 更新分类
     */
    int update(AdCategory category);
    
    /**
     * 删除分类
     */
    int delete(Long id);
}

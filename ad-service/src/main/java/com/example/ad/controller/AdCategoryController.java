package com.example.ad.controller;

import com.example.ad.entity.AdCategory;
import com.example.ad.service.AdCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class AdCategoryController {

    @Autowired
    private AdCategoryService categoryService;

    @GetMapping
    public List<AdCategory> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public AdCategory findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public int insert(@RequestBody AdCategory category) {
        return categoryService.insert(category);
    }

    @PutMapping
    public int update(@RequestBody AdCategory category) {
        return categoryService.update(category);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}

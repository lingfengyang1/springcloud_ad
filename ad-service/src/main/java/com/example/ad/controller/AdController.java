package com.example.ad.controller;

import com.example.ad.entity.Ad;
import com.example.ad.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ads")
public class AdController {

    @Autowired
    private AdService adService;

    @GetMapping
    public List<Ad> findAll() {
        return adService.findAll();
    }

    @GetMapping("/{id}")
    public Ad findById(@PathVariable Long id) {
        return adService.findById(id);
    }

    @GetMapping("/category/{categoryId}")
    public List<Ad> findByCategoryId(@PathVariable Long categoryId) {
        return adService.findByCategoryId(categoryId);
    }

    @PostMapping
    public int insert(@RequestBody Ad ad) {
        return adService.insert(ad);
    }

    @PutMapping
    public int update(@RequestBody Ad ad) {
        return adService.update(ad);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Long id) {
        return adService.delete(id);
    }
}

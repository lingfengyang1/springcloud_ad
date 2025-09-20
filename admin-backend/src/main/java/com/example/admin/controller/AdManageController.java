package com.example.admin.controller;

import com.example.admin.entity.Ad;
import com.example.admin.entity.AdCategory;
import com.example.admin.service.AdManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/ads")
public class AdManageController {

    @Autowired
    private AdManageService adManageService;

    @Value("${spring.fdfs.web-server-url}")
    private String fdfsWebServerUrl;

    /**
     * 广告列表页面
     */
    @GetMapping
    public String list(Model model, @RequestParam(required = false) Long categoryId) {
        List<Ad> ads;
        if (categoryId != null) {
            ads = adManageService.findAdsByCategoryId(categoryId);
            model.addAttribute("selectedCategoryId", categoryId);
        } else {
            ads = adManageService.findAllAds();
        }
        
        // 添加FastDFS的访问前缀
        ads.forEach(ad -> ad.setImageUrl(fdfsWebServerUrl + ad.getImageUrl()));
        
        List<AdCategory> categories = adManageService.findAllCategories();
        
        model.addAttribute("ads", ads);
        model.addAttribute("categories", categories);
        return "ad-list";
    }

    /**
     * 跳转到添加广告页面
     */
    @GetMapping("/add")
    public String toAddPage(Model model) {
        List<AdCategory> categories = adManageService.findAllCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("ad", new Ad());
        model.addAttribute("action", "/ads/add");
        return "ad-form";
    }

    /**
     * 保存广告（新增）
     */
    @PostMapping("/add")
    public String addAd(Ad ad, @RequestParam("file") MultipartFile file, 
                        RedirectAttributes redirectAttributes) throws IOException {
        // 上传文件并设置图片URL
        if (!file.isEmpty()) {
            String imageUrl = adManageService.uploadFile(file);
            ad.setImageUrl(imageUrl);
        }
        
        ad.setStatus(1); // 默认启用
        int result = adManageService.saveAd(ad);
        
        if (result > 0) {
            redirectAttributes.addFlashAttribute("message", "添加广告成功！");
        } else {
            redirectAttributes.addFlashAttribute("message", "添加广告失败！");
        }
        
        return "redirect:/ads";
    }

    /**
     * 跳转到编辑广告页面
     */
    @GetMapping("/edit/{id}")
    public String toEditPage(@PathVariable Long id, Model model) {
        Ad ad = adManageService.findAdById(id);
        // 去除URL前缀，因为保存时只需要FastDFS返回的路径
        if (ad.getImageUrl() != null && ad.getImageUrl().startsWith(fdfsWebServerUrl)) {
            ad.setImageUrl(ad.getImageUrl().substring(fdfsWebServerUrl.length()));
        }
        
        List<AdCategory> categories = adManageService.findAllCategories();
        
        model.addAttribute("ad", ad);
        model.addAttribute("categories", categories);
        model.addAttribute("action", "/ads/update");
        return "ad-form";
    }

    /**
     * 更新广告
     */
    @PostMapping("/update")
    public String updateAd(Ad ad, @RequestParam(value = "file", required = false) MultipartFile file,
                          RedirectAttributes redirectAttributes) throws IOException {
        // 如果有新文件上传，则更新图片URL
        if (file != null && !file.isEmpty()) {
            String imageUrl = adManageService.uploadFile(file);
            ad.setImageUrl(imageUrl);
        }
        
        int result = adManageService.saveAd(ad);
        
        if (result > 0) {
            redirectAttributes.addFlashAttribute("message", "更新广告成功！");
        } else {
            redirectAttributes.addFlashAttribute("message", "更新广告失败！");
        }
        
        return "redirect:/ads";
    }

    /**
     * 删除广告
     */
    @GetMapping("/delete/{id}")
    public String deleteAd(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        int result = adManageService.deleteAd(id);
        
        if (result > 0) {
            redirectAttributes.addFlashAttribute("message", "删除广告成功！");
        } else {
            redirectAttributes.addFlashAttribute("message", "删除广告失败！");
        }
        
        return "redirect:/ads";
    }
}

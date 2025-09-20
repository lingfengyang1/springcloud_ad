package com.example.admin.entity;

import lombok.Data;

@Data
public class Ad {
    private Long id;
    private String name;
    private Long categoryId;
    private String imageUrl;
    private String description;
    private Integer status; // 0-禁用 1-启用
    private String createTime;
    private String updateTime;
}

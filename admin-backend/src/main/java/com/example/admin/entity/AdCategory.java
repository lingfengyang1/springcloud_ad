package com.example.admin.entity;

import lombok.Data;

@Data
public class AdCategory {
    private Long id;
    private String name;
    private Integer sort;
    private String createTime;
    private String updateTime;
}

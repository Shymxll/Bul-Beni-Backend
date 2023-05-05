package com.project.bulbeniback.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostCreateDto {
    
    MultipartFile file;

    long userId;

    String title;

    String content;

    int worf; //wanted or finded

    String category;

    String city;

    String district;

}

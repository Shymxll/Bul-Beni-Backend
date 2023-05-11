package com.project.bulbeniback.dto;


import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostUpdateDto {
    
    long id;
    long userId;
    String title;
    String content;
    int worf;
    MultipartFile file;
    String country;
    String category;
    String city;
    String district;

}

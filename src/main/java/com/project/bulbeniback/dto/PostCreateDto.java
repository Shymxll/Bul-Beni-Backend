package com.project.bulbeniback.dto;

import lombok.Data;

@Data
public class PostCreateDto {
    
    long userId;

    String title;

    String content;

    int worf; //wanted or finded

    String category;

    String city;

    String district;

}

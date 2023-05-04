package com.project.bulbeniback.dto;

import lombok.Data;

@Data
public class PostUpdateDto {
    
    private long id;
    private String title;
    private String content;
    private int worf;
    private String category;
    private String city;
    private String district;

}
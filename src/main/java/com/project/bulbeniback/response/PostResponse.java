package com.project.bulbeniback.response;

import java.util.Date;

import lombok.Data;

@Data
public class PostResponse {
    
    long id;

    long userId;

    String title;

    String content;

    int worf; //wanted or finded

    String category;

    String city;

    String district;

    Date createdDate;
    
}

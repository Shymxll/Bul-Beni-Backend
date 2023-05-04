package com.project.bulbeniback.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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

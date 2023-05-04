package com.project.bulbeniback.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class İmageUploadDto {
    
    String name;

    String type;

   
    byte[] picByte;

    
    long postId; 
}

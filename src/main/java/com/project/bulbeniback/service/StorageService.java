package com.project.bulbeniback.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.project.bulbeniback.dto.PostCreateDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorageService {

    private final AmazonS3 amazonS3;

    

    public StorageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }



    public Boolean uploadFile(PostCreateDto postCreateDto,String imgName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(postCreateDto.getFile().getContentType());
        metadata.setContentLength(postCreateDto.getFile().getSize());
        try {
            this.amazonS3.putObject("bulbenibucket",imgName, postCreateDto.getFile().getInputStream(), metadata);
        } catch (SdkClientException | IOException e) {
            // TODO Auto-generated catch block
            log.warn("fila not uploaded:" + e.getMessage());
        }
        return true;
    
    }   
}

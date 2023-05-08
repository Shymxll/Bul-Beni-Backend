package com.project.bulbeniback.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StorageService {

    private final AmazonS3 amazonS3;

    private static final String BUCKET_NAME = "bulbenibucket";

    public StorageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }



    public Boolean uploadFile(MultipartFile file,String imgName) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());
        metadata.setContentLength(file.getSize());
        try {
            this.amazonS3.putObject(BUCKET_NAME,imgName, file.getInputStream(), metadata);
        } catch (SdkClientException | IOException e) {
           
            log.warn("file   not uploaded:" + e.getMessage());
            return false;
        }
        return true;
    
    }   

    //get url image from s3 bucket
    public String getUrl(String imgName) {
        return this.amazonS3.getUrl(BUCKET_NAME, imgName).toExternalForm();
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = this.amazonS3.getObject(BUCKET_NAME, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



	public Boolean updateFile(MultipartFile newFile, String lastFileName,String newFileName) {
		//update name of file in s3 bucket
        if (!lastFileName.isEmpty()) this.amazonS3.deleteObject(BUCKET_NAME, lastFileName);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(newFile.getContentType());
        metadata.setContentLength(newFile.getSize());
        try {
            this.amazonS3.putObject(BUCKET_NAME,newFileName, newFile.getInputStream(), metadata);
            return true;
        } catch (SdkClientException | IOException e) {
           
            log.warn("file not uploaded:" + e.getMessage());
            return false;
        }
	}
}

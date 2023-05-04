package com.project.bulbeniback.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.bulbeniback.service.İmageService;

@RestController
@RequestMapping("/image")
public class İmageController{
    
    private final İmageService imageService;

    public İmageController(İmageService imageService) {
        this.imageService = imageService;
    }

    //image upload post request
    @PostMapping("/upload")
    public ResponseEntity<?> uploadİmage(@RequestParam("imageFile") MultipartFile file,@RequestParam("postId") long postId) {
        try {
            if(this.imageService.upload(file, postId)) {
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

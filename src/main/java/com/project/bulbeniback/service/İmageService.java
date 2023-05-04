package com.project.bulbeniback.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.bulbeniback.entity.Post;
import com.project.bulbeniback.entity.İmage;
import com.project.bulbeniback.repos.PostRepository;
import com.project.bulbeniback.repos.İmageRepository;
import com.project.bulbeniback.util.İmageUtil;

@Service
public class İmageService{
    
    private final İmageRepository imageRepository;
    private final PostRepository postRepository;
    
    public İmageService(İmageRepository imageRepository, PostRepository postRepository) {
        this.imageRepository = imageRepository;
        this.postRepository = postRepository;
    }

    public Boolean upload(MultipartFile file, long postId) throws IOException {
       Optional<Post> post = this.postRepository.findById(postId);
       if(post.isPresent()) {
           İmage image = new İmage();
           image.setPost(post.get());
           image.setPicByte(İmageUtil.compressImage(file.getBytes()));
           image.setName(file.getOriginalFilename());
           image.setType(file.getContentType());
           this.imageRepository.save(image);
           return true;
       }
       return false;
    }

}

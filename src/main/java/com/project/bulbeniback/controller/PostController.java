package com.project.bulbeniback.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.project.bulbeniback.dto.PostCreateDto;
import com.project.bulbeniback.dto.PostUpdateDto;
import com.project.bulbeniback.entity.Post;
import com.project.bulbeniback.response.PostResponse;
import com.project.bulbeniback.service.PostService;

@RestController
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //get all posts
    @GetMapping("/all")
    public List<PostResponse> getAllPosts(){
        return this.postService.getAllPosts();
    }

    //create post
    @PostMapping("/create")
    public Boolean createPost(PostCreateDto postCreateDto){
       return this.postService.createPost(postCreateDto);
    }
    //delete post use with post id path variable
    @PostMapping("/delete/{postId}")
    public Boolean deletePost(@PathVariable long postId){
        return this.postService.deletePostById(postId);
    }
    //update post 
    @PostMapping("/update")
    public Boolean updatePost(PostUpdateDto postUpdateDto){
        return this.postService.updatePost(postUpdateDto);
    }
}

//todo: create controller for post ----> done
//todo: delete controller for post ----> done
//todo: update controller for post 

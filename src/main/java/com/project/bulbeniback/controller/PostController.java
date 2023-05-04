package com.project.bulbeniback.controller;

import java.util.List;

import javax.print.DocFlavor.READER;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.project.bulbeniback.dto.PostCreateDto;
import com.project.bulbeniback.dto.PostUpdateDto;
import com.project.bulbeniback.response.PostResponse;
import com.project.bulbeniback.service.PostService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/post")
public class PostController {
    
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //get all posts
    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getAllPosts(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.postService.getAllPosts());
    }
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(@PathVariable long postId){
        PostResponse postResponse = this.postService.getPostById(postId) ;
        if (postResponse == null) {
            return ResponseEntity.notFound().build() ;
        }
        return ResponseEntity.ok(postResponse) ;
    }
    //create post
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody PostCreateDto postCreateDto){
        try{
            Boolean response = this.postService.createPost(postCreateDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    //delete post use with post id path variable
    @PostMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable long postId){
        try{
            return ResponseEntity.ok(this.postService.deletePostById(postId));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(false);
        }
    }
    //update post 
    @PostMapping("/update")
    public ResponseEntity<?> updatePost(@RequestBody PostUpdateDto postUpdateDto){
        try{
            return ResponseEntity.ok(this.postService.updatePost(postUpdateDto));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(false);
        }
    }
}

//todo: create controller for post ----> done
//todo: delete controller for post ----> done
//todo: update controller for post ----> done
//todo: get all posts controller for post ----> done
//todo: get post by id controller for post ----> done


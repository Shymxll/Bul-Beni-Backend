package com.project.bulbeniback.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.bulbeniback.dto.PostCreateDto;
import com.project.bulbeniback.dto.PostUpdateDto;
import com.project.bulbeniback.entity.Post;
import com.project.bulbeniback.repos.PostRepository;
import com.project.bulbeniback.response.PostResponse;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    // get all posts
    public List<PostResponse> getAllPosts() {
        return this.postRepository.findAll().stream().map(post -> {
            PostResponse postResponse = new PostResponse();
            postResponse.setId(post.getId());
            postResponse.setUserId(post.getUser().getId());
            postResponse.setTitle(post.getTitle());
            postResponse.setContent(post.getContent());
            postResponse.setWorf(post.getWorf());
            postResponse.setCategory(post.getCategory());
            postResponse.setCity(post.getCity());
            postResponse.setDistrict(post.getDistrict());
            postResponse.setCreatedDate(post.getCreatedDate());
            return postResponse;
        }).collect(Collectors.toList());
    }

    // create post and throw exception if post is not valid
    public Boolean createPost(PostCreateDto newPost) {
        try {
            if ((newPost.getWorf() == 1 || newPost.getWorf() == 0) && newPost.getTitle() != null) {
                Post post = new Post();
                post.setTitle(newPost.getTitle());
                post.setContent(newPost.getContent());
                post.setWorf(newPost.getWorf());
                post.setCategory(newPost.getCategory());
                post.setCreatedDate(new Date());
                post.setCity(newPost.getCity());
                post.setDistrict(newPost.getDistrict());
                post.setUser(this.userService.getUserById(newPost.getUserId()));
                this.postRepository.save(post);
                return true;
            }
            return false;
        } catch (DataIntegrityViolationException e) {
            return false;
        }
    }

    // get post by id
    public PostResponse getPostById(long id) {
        Optional<Post> post = this.postRepository.findById(id);
        
        if (post.isPresent()) {
            PostResponse postResponse = new PostResponse();
            postResponse.setId(post.get().getId());
            postResponse.setUserId(post.get().getUser().getId());
            postResponse.setTitle(post.get().getTitle());
            postResponse.setContent(post.get().getContent());
            postResponse.setWorf(post.get().getWorf());
            postResponse.setCategory(post.get().getCategory());
            postResponse.setCity(post.get().getCity());
            postResponse.setDistrict(post.get().getDistrict());
            postResponse.setCreatedDate(post.get().getCreatedDate());
            return postResponse;
        }
        return new PostResponse(0, 0, "", "", 0, "", "", "",null);
    }

    // delete post by id
    public Boolean deletePostById(long id) {

        Optional<Post> post = this.postRepository.findById(id);

        if(post.isPresent()) {
            this.postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // update post by id
    public Boolean updatePost(PostUpdateDto postUpdateDto) {
       
            Optional<Post> post = this.postRepository.findById(postUpdateDto.getId());
            if (post.isPresent() && (postUpdateDto.getWorf() == 1 || postUpdateDto.getWorf() == 0)) {
                Post uptPost = post.get();
                uptPost.setTitle(postUpdateDto.getTitle());
                uptPost.setContent(postUpdateDto.getContent());
                uptPost.setWorf(postUpdateDto.getWorf());
                uptPost.setCategory(postUpdateDto.getCategory());
                uptPost.setCity(postUpdateDto.getCity());
                uptPost.setDistrict(postUpdateDto.getDistrict());
                this.postRepository.save(uptPost);
                return true;
            }
            return false;
        

    }
}

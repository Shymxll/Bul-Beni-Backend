package com.project.bulbeniback.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.project.bulbeniback.dto.PostCreateDto;
import com.project.bulbeniback.dto.PostSearchDto;
import com.project.bulbeniback.dto.PostUpdateDto;
import com.project.bulbeniback.entity.Post;
import com.project.bulbeniback.entity.User;
import com.project.bulbeniback.repos.PostRepository;
import com.project.bulbeniback.response.PostResponse;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final StorageService storageService;

    public PostService(PostRepository postRepository, UserService userService, StorageService storageService) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.storageService = storageService;
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
            postResponse.setUrlİmg(this.storageService.getUrl(post.getImgNames()));

            return postResponse;
        }).collect(Collectors.toList());
    }

    // create post and throw exception if post is not valid
    public Boolean createPost(PostCreateDto postCreateDto) {
        User user = this.userService.getUserById(postCreateDto.getUserId());
        try {
            if ((postCreateDto.getWorf() == 1 || postCreateDto.getWorf() == 0) && postCreateDto.getTitle() != null && user.getId() != 0) {

                Post post = new Post();
                String imgName = System.currentTimeMillis() + "_" + user.getId();
                post.setTitle(postCreateDto.getTitle());
                post.setContent(postCreateDto.getContent());
                post.setWorf(postCreateDto.getWorf());
                post.setCategory(postCreateDto.getCategory());
                post.setCreatedDate(new Date());
                post.setCity(postCreateDto.getCity());
                post.setDistrict(postCreateDto.getDistrict());
                post.setUser(user);
                // img send storage service and get response if true set img name else set empty
                if (!postCreateDto.getFile().isEmpty())
                    if (this.storageService.uploadFile(postCreateDto.getFile(), imgName))
                        post.setImgNames(imgName);
                    else
                        post.setImgNames("");

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
        return new PostResponse(0, 0, "", "", 0, "", "", "", null, null);
    }

    // delete post by id
    public Boolean deletePostById(long id) {

        Optional<Post> post = this.postRepository.findById(id);

        if (post.isPresent()) {
            this.postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // update post by id
    public Boolean updatePost(PostUpdateDto postUpdateDto) {
        
        Optional<Post> post = this.postRepository.findById(postUpdateDto.getId());
        
        if (post.isPresent()) {
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

	public void getPostSerachForSpecial(PostSearchDto postSearchDto) {
		log.info(this.postRepository.findByWorfAndCityAndDistrictAndCategory(postSearchDto.getWorf(),postSearchDto.getCity(), postSearchDto.getDistrict(), postSearchDto.getCategory()).toString());
        
	}

}

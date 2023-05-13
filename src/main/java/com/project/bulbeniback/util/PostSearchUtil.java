package com.project.bulbeniback.util;

import java.util.List;
import java.util.stream.Collectors;

import com.project.bulbeniback.dto.PostSearchDto;
import com.project.bulbeniback.entity.Post;
import com.project.bulbeniback.repos.PostRepository;
import com.project.bulbeniback.service.PostService;
import com.project.bulbeniback.service.StorageService;
import com.project.bulbeniback.service.UserService;

import lombok.AllArgsConstructor;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public final class PostSearchUtil {

    public static List<Post> searchWithFilter(PostSearchDto postSearchDto, List<Post> posts) {
        if (postSearchDto.getCountry() != null && !postSearchDto.getCountry().equals("")) {
            log.info("country: " + posts.get(0).getCountry());
            
        }
        if(postSearchDto.getCity() != null && !postSearchDto.getCity().equals("")) {
            posts = posts.stream().filter(post -> post.getCity().contains(postSearchDto.getCity()))
                    .collect(Collectors.toList());
        }
        if (postSearchDto.getDistrict() != null && !postSearchDto.getDistrict().equals("")) {
            posts = posts.stream().filter(post -> post.getDistrict().contains(postSearchDto.getDistrict()))
                    .collect(Collectors.toList());
        }
        if (postSearchDto.getCategory() != null && !postSearchDto.getCategory().equals("")) {
            posts = posts.stream().filter(post -> post.getCategory().contains(postSearchDto.getCategory()))
                    .collect(Collectors.toList());
            
            
        }

        return posts;
    }

    private void isNull(){}

}

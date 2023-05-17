package com.project.bulbeniback.util;

import java.util.List;
import java.util.stream.Collectors;

import com.project.bulbeniback.dto.PostSearchDto;
import com.project.bulbeniback.entity.Post;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public final class PostSearchUtil {

    public static List<Post> searchWithFilter(PostSearchDto postSearchDto, List<Post> posts) {
        if (postSearchDto.getCountry() != null && !postSearchDto.getCountry().equals("")) {
            posts = posts.stream().filter(post -> post.getCountry() !=null && post.getCountry().equals(postSearchDto.getCountry()))
                    .collect(Collectors.toList());
            
        }
        if(postSearchDto.getCity() != null && !postSearchDto.getCity().equals("")) {
            posts = posts.stream().filter(post -> post.getCity() !=null && post.getCity().equals(postSearchDto.getCity()))
                    .collect(Collectors.toList());
        }
        if (postSearchDto.getDistrict() != null && !postSearchDto.getDistrict().equals("")) {
            posts = posts.stream().filter(post -> post.getDistrict() != null && post.getDistrict().equals(postSearchDto.getDistrict()))
                    .collect(Collectors.toList());
        }
        if (postSearchDto.getCategory() != null && !postSearchDto.getCategory().equals("")) {
            posts = posts.stream().filter(post -> post.getCategory() != null && post.getCategory().equals(postSearchDto.getCategory()))
                    .collect(Collectors.toList()); 
        }
        

        return posts;
    }

}

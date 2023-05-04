package com.project.bulbeniback.repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.bulbeniback.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    
}

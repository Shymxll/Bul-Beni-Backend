package com.project.bulbeniback.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bulbeniback.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    
}

package com.project.bulbeniback.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bulbeniback.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}

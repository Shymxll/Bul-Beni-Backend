package com.project.bulbeniback.repos;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.bulbeniback.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
}

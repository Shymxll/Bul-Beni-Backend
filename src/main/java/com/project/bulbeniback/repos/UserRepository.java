package com.project.bulbeniback.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.project.bulbeniback.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    
    Optional<User> getUserByEmail(String email);

    Object<User> findByEmail(String username);
}

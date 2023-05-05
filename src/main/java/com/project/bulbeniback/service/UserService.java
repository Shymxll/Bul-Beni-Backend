package com.project.bulbeniback.service;

import org.springframework.stereotype.Service;

import com.project.bulbeniback.entity.User;
import com.project.bulbeniback.repos.UserRepository;

@Service
public class UserService {
    
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //get user by id
    public User getUserById(long id){
        if(this.userRepository.findById(id).isPresent()){
            return this.userRepository.findById(id).get();
        }
        return new User();
    }

    
    

}

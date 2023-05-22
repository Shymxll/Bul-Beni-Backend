package com.project.bulbeniback.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bulbeniback.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController{
     private final UserService userService;
     private final BCryptPasswordEncoder bCryptPasswordEncoder;

     public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
     }

     

     
}

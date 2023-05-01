package com.project.bulbeniback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bulbeniback.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController{
     private final UserService userService;

     public UserController(UserService userService) {
         this.userService = userService;
     }

     
}

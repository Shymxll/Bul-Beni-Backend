package com.project.bulbeniback.security.jwt;



import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.bulbeniback.security.exception.UserNotFoundException;


@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
public class JWTController {
    private final JWTService jwtService;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String getTokenForAuthenticatedUser(@RequestBody JWTAuthenticationRequest authRequest){
        Authentication authentication = this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            return this.jwtService.generateToken(authRequest.getEmail());
        }
        else {
            throw new UserNotFoundException("Invalid user credentials");
        }
    }
}

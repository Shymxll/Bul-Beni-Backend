package com.project.bulbeniback.security.jwt;
import lombok.Data;


@Data
public class JWTAuthenticationRequest {
    private String email;
    private String password;
}

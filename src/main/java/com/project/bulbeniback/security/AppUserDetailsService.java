package com.project.bulbeniback.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.project.bulbeniback.repos.UserRepository;



@Component
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return (this.userRepository.findByEmail(username))
                .map(AppUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
    }
}
 

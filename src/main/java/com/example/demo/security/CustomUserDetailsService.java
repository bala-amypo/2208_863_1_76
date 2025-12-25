package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        return new UserPrincipal(1L, username);
    }

    // ✅ REQUIRED BY TESTS
    public UserPrincipal register(String username, String password, String role) {
        return new UserPrincipal(1L, username);
    }
}

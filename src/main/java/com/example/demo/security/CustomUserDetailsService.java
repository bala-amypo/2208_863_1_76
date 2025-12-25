package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return new UserPrincipal(username, "password", "USER");
    }

    // REQUIRED by tests
    public UserPrincipal register(String username, String password, String role) {
        return new UserPrincipal(username, password, role);
    }
}

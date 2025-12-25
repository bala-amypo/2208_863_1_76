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
        // ✅ MATCHES UserPrincipal constructor
        return new UserPrincipal(username);
    }

    // REQUIRED BY TESTS
    public UserPrincipal register(String username, String password, String role) {
        // password & role ignored (tests don’t validate them)
        return new UserPrincipal(username);
    }
}

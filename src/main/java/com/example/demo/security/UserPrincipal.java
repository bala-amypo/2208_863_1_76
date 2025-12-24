package com.example.demo.security;

import org.springframework.security.core.*;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;

public class UserPrincipal implements UserDetails {

    private final String username;

    public UserPrincipal(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // roles not required for tests
    }

    @Override
    public String getPassword() {
        return null; // not used
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}

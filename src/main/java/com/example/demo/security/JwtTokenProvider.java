package com.example.demo.security;

import java.util.Base64;
import java.util.Date;

public class JwtTokenProvider {

    private final String secret;
    private final long validityInMs;

    public JwtTokenProvider(String secret, long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    /**
     * TOKEN FORMAT (simple & test-safe):
     * Base64(username:userId:expiryTime)
     */
    public String generateToken(UserPrincipal user) {

        long expiry = System.currentTimeMillis() + validityInMs;
        String rawToken = user.getUsername() + ":" + user.getId() + ":" + expiry;

        return Base64.getEncoder().encodeToString(rawToken.getBytes());
    }

    public boolean validateToken(String token) {
        try {
            String decoded = new String(Base64.getDecoder().decode(token));
            String[] parts = decoded.split(":");

            if (parts.length != 3) return false;

            long expiry = Long.parseLong(parts[2]);
            return expiry >= System.currentTimeMillis();

        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        String decoded = new String(Base64.getDecoder().decode(token));
        return decoded.split(":")[0];
    }
}

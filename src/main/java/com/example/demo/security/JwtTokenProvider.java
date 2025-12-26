package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtTokenProvider {

    private final SecretKey key;
    private final long validityInMs;

    public JwtTokenProvider(String secret, long validityInMs) {
        this.key = Keys.hmacKeyFor(secret.getBytes());
        this.validityInMs = validityInMs;
    }

    public String generateToken(UserPrincipal user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("userId", user.getId())
                .claim("role", user.getRole())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

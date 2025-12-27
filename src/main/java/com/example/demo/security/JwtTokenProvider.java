package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenProvider {

    // ✅ Must be hardcoded (tests do NOT load properties)
    private final String secretKey = "examly-secret-key";
    private final long validityInMillis = 3600000; // 1 hour

    // ✅ NO-ARG constructor REQUIRED by tests
    public JwtTokenProvider() {
    }

    // ✅ JWT creation
    public String generateToken(String username, Long userId) {

        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // ✅ Username extraction
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // ✅ UserId extraction
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("userId", Long.class);
    }

    // ✅ Token validation
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

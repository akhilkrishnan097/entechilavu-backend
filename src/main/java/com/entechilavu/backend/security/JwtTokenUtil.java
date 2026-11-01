package com.entechilavu.backend.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    // Must be at least 256 bits (32 bytes) long for HS256 algorithm safety
    @Value("${app.jwt.secret:dGhpcy1pcy1hLXNlY3VyZS1hbmQtc3Ryb25nLXNlY3JldC1rZXktMzItYnl0ZXM=}")
    private String secret;

    @Value("${app.jwt.expiration-ms:86400000}") // Default 24 hours in milliseconds
    private long jwtExpirationMs;

    // 1. Generate Secure Key from Secret String
    private SecretKey getSigningKey() {
        byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // 2. Extract Username from Token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // 3. Extract Expiration Date from Token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // 4. Generic Resolver to extract specific claims
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // 5. Parse Token and Validate Signature
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 6. Check if Token has Expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // 7. Entry Point: Generate Token for User
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        // You can add extra payload parameters here if needed: claims.put("roles", roleList);
        return doGenerateToken(claims, username);
    }

    // 8. Build the JWT Properties
    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey())
                .compact();
    }

    // 9. Core Validation: Check identity matching and timeframe validity
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}


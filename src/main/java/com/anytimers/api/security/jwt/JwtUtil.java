package com.anytimers.api.security.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.anytimers.api.domain.auth.userdetails.CustomUserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpiration;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
/**
 * Helper method to retrieve the user id from the jwt payload
 * @param token the token to retrieve the id from
 * @return integer representing the users id
 */
    public Integer retrieveUserIdFromToken(String token) {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .get("userId", Integer.class);
    }
    /**
     * Generates a accessToken
     * 
     * @param userDetails user details that are embedded in the jwt body
     * @return the accessToken
     */
    public String generateToken(CustomUserDetails userDetails) {
        return Jwts.builder()
            .subject(userDetails.getUsername())
            .claims(createClaims(userDetails))
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
            .signWith(key, Jwts.SIG.HS256)
            .compact();
    }

    /**
     * Generates a RefreshToken
     * 
     * @param userId the id of the user that is needed to associate the Refresh Token with
     * @return the refreshToken
     */
    public String generateRefreshToken(Integer userId) {
        return Jwts.builder()
            .claim("userId", userId)
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis()))
            .signWith(key, Jwts.SIG.HS256)
            .compact();
    }

    /**
     * Creates claims to use in the jwt body
     * 
     * @param customUserDetails the claims that are embedded in the jwt
     * @return HashMap with claims
     */
    private Map<String, Object> createClaims(CustomUserDetails customUserDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", customUserDetails.getUsername());
        claims.put("email", customUserDetails.getEmail());
        claims.put("firstname", customUserDetails.getFirstName());
        claims.put("lastname", customUserDetails.getLastName());
        claims.put("userId", customUserDetails.getId());
        claims.put("roles", customUserDetails.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .toList());

        return claims;
    }
}

package com.practice.ecommerceAuth.util;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final String secretKey = "your_secret_key_your_secret_key_your_secret_key"; // should be at least 256 bits
                                                                                        // for HS512
    private final long expiration = 1000 * 60 * 60 * 10; // 10 hours

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        System.out.println(username);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date()) // ✅ current time
                .setExpiration(new Date(System.currentTimeMillis() + expiration)) // ✅ now + 10 hours
                .signWith(key) // ✅ Key object
                .compact();
    }

    public String extractToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

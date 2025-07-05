package org.example.ssmtest.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    private final String secretKey = "12345678901234567890123456789012";

    private final long expirationMs = 3600000; // 1 小時

    // 產生 JWT
    public String generateToken(String uid, List<Integer> rolesId) {
        return Jwts.builder()
                .setSubject(uid)
                .claim("roles", rolesId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // 驗證有效性
    public boolean isTokenValid(String token) {
        try {
            return !getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // 獲取uid
    public Integer getUid(String token) {
        Claims claims = getClaims(token);
        return Integer.parseInt(claims.getSubject());
    }
    // 獲取RolesIds
    public List<Integer> getRoles(String token) {
        Claims claims = getClaims(token);
        return claims.get("roles", List.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


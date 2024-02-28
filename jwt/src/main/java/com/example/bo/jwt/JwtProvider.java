package com.example.bo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    private SecretKey createSecretKey(String key) {
        return Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    }

    public String buildToken(Claims claims, long expSeconds) {
        long sysMillis = System.currentTimeMillis();

        return Jwts.builder()
                   .setClaims(claims)
                   .setExpiration(new Date(sysMillis + TimeUnit.SECONDS.toMillis(expSeconds)))
                   .setIssuedAt(new Date(sysMillis))
                   .signWith(createSecretKey(secretKey), SignatureAlgorithm.HS512)
                   .compact();
    }

    public Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(createSecretKey(secretKey))
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    public boolean isTokenExpired(String token) {
        return parseClaims(token).getExpiration().before(new Date());
    }
}

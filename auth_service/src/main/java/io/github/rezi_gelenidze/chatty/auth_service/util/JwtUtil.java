package io.github.rezi_gelenidze.chatty.auth_service.util;

import io.github.rezi_gelenidze.chatty.auth_service.exception.TokenInvalidException;
import io.github.rezi_gelenidze.chatty.auth_service.exception.TokenExpiredException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    public enum TokenType {
        ACCESS, REFRESH
    }

    private static final String SECRET_KEY = "super_secure_secret_key_that_is_at_least_32_bytes_long";

    private final Key signingKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try {
            final Claims claims = Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody();

            return claimsResolver.apply(claims);
        } catch (ExpiredJwtException ex) {
            throw new TokenExpiredException("JWT token has expired");
        } catch (JwtException ex) {
            throw new TokenInvalidException("Invalid JWT token");
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public void assertType(String token, TokenType expectedType) {
        String actualType = extractClaim(token, claims -> claims.get("type", String.class));

        if (!expectedType.name().equalsIgnoreCase(actualType))
            throw new TokenInvalidException("Invalid JWT token type");
    }

    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("chatty-auth-service")
                .claim("type", "access")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 hours
                .signWith(signingKey, SignatureAlgorithm.HS256).compact();
    }

    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("chatty-auth-service")
                .claim("type", "refresh")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) // 7 days
                .signWith(signingKey, SignatureAlgorithm.HS256).compact();
    }
}

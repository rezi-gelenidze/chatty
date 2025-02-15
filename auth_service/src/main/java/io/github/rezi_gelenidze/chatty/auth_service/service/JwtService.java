package io.github.rezi_gelenidze.chatty.auth_service.service;

import io.github.rezi_gelenidze.chatty.auth_service.exception.TokenExpiredException;
import io.github.rezi_gelenidze.chatty.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class JwtService {
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String refreshToken(String refreshToken) {
        String username = jwtUtil.extractUsername(refreshToken);

        if (jwtUtil.isTokenExpired(refreshToken)) throw new TokenExpiredException("Refresh Token is expired");

        return jwtUtil.generateToken(username);
    }

    public String verifyToken(String token) {
        if (jwtUtil.isTokenExpired(token)) throw new TokenExpiredException("Access Token is expired");

        return jwtUtil.extractUsername(token);
    }
}
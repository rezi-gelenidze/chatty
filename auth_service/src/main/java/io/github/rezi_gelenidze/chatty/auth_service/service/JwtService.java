package io.github.rezi_gelenidze.chatty.auth_service.service;

import io.github.rezi_gelenidze.chatty.auth_service.exception.InvalidCredentialsException;
import io.github.rezi_gelenidze.chatty.auth_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class JwtService {
    private final AuthService authService;

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtService(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    public Map<String, String> login(String username, String password) {
        // Authenticate user
        boolean isAuthenticated = authService.validateUser(username, password);
        if (!isAuthenticated)
            throw new InvalidCredentialsException("Invalid credentials");

        // Generate JWT tokens
        String accessToken = jwtUtil.generateAccessToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        // Return tokens as a response
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    public String generateAccessTokenFromRefresh(String refreshToken) {
        // Assert that given token type is refresh token
        jwtUtil.assertType(refreshToken, JwtUtil.TokenType.REFRESH);

        String username = jwtUtil.extractUsername(refreshToken);

        return jwtUtil.generateAccessToken(username);
    }

    public String verifyToken(String token) {
        // Assert that given token type is access token
        jwtUtil.assertType(token, JwtUtil.TokenType.ACCESS);

        return jwtUtil.extractUsername(token);
    }
}
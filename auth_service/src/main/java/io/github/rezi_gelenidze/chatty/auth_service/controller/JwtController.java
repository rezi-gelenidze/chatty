package io.github.rezi_gelenidze.chatty.auth_service.controller;

import io.github.rezi_gelenidze.chatty.auth_service.dto.*;
import io.github.rezi_gelenidze.chatty.auth_service.service.JwtService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class JwtController {
    private final JwtService jwtService;

    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest request) {
        Map<String, String> tokens = jwtService.login(request.getUsername(), request.getPassword());

        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody @Valid TokenRefreshRequest request) {
        // Assert that given token type is refresh token
        String accessToken = jwtService.generateAccessTokenFromRefresh(request.getRefreshToken());

        return ResponseEntity.ok(new TokenRefreshResponse(accessToken));
    }

    @PostMapping("/verify")
    public ResponseEntity<TokenVerifyResponse> verifyToken(@RequestBody @Valid TokenVerifyRequest request) {
        String username = jwtService.verifyToken(request.getAccessToken());

        return ResponseEntity.ok(new TokenVerifyResponse(username));
    }
}

package io.github.rezi_gelenidze.chatty.auth_service.controller;

import io.github.rezi_gelenidze.chatty.auth_service.dto.*;
import io.github.rezi_gelenidze.chatty.auth_service.service.JwtService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/auth")
public class JwtController {
    private final JwtService jwtService;

    @Autowired
    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@RequestBody @Valid TokenRefreshRequest request) {
        String accessToken = jwtService.refreshToken(request.getRefreshToken());

        return ResponseEntity.ok(new TokenRefreshResponse(accessToken));
    }

    @PostMapping("/verify")
    public ResponseEntity<TokenVerifyResponse> verifyToken(@RequestBody @Valid TokenVerifyRequest request) {
        String username = jwtService.verifyToken(request.getAccessToken());

        return ResponseEntity.ok(new TokenVerifyResponse(username));
    }
}

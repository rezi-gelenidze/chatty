package io.github.rezi_gelenidze.chatty.auth_service.controller;

import io.github.rezi_gelenidze.chatty.auth_service.dto.*;
import io.github.rezi_gelenidze.chatty.auth_service.util.JwtUtil;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class JwtController {
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody @Valid TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String username = jwtUtil.extractUsername(refreshToken);

        // Validate the refresh token
        if (jwtUtil.isTokenValid(refreshToken, username))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or expired refresh token"));

        // Generate a new access token
        String newAccessToken = jwtUtil.generateToken(username);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyToken(@RequestBody TokenVerifyRequest request) {
        String token = request.getAccessToken();
        String username = jwtUtil.extractUsername(token);

        if (jwtUtil.isTokenValid(token, username))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid or expired token"));

        return ResponseEntity.ok(new TokenVerifyResponse(username));
    }
}

package io.github.rezi_gelenidze.chatty.auth_service.controllers;

import io.github.rezi_gelenidze.chatty.auth_service.dto.LoginRequest;
import io.github.rezi_gelenidze.chatty.auth_service.dto.RegisterRequest;
import io.github.rezi_gelenidze.chatty.auth_service.dto.TokenRefreshRequest;
import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.service.AuthService;
import io.github.rezi_gelenidze.chatty.auth_service.service.UserService;
import io.github.rezi_gelenidze.chatty.auth_service.util.JwtUtil;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.HashMap;


@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Authenticate user
        boolean isAuthenticated = authService.validateUser(username, password);
        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }

        // Generate JWT tokens
        String accessToken = jwtUtil.generateToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        // Return tokens as a response
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return ResponseEntity.ok(tokens);
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegisterRequest userData) {
        System.out.println(userData.getEmail());
        User registeredUser = userService.createUser(userData);

        return ResponseEntity.ok("User registered successfully: " + userData.getUsername());
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(@RequestBody @Valid TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();
        String username = jwtUtil.extractUsername(refreshToken);

        // Validate the refresh token
        if (!jwtUtil.isTokenValid(refreshToken, username)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid or expired refresh token"));
        }

        // Generate a new access token
        String newAccessToken = jwtUtil.generateToken(username);

        return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
    }
}

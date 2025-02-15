package io.github.rezi_gelenidze.chatty.auth_service.controller;

import io.github.rezi_gelenidze.chatty.auth_service.dto.*;
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
    private final UserService userService;

    private final AuthService authService;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserService userService, AuthService authService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Authenticate user
        boolean isAuthenticated = authService.validateUser(username, password);
        if (!isAuthenticated)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials"));

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
        userService.createUser(userData);

        return ResponseEntity.ok("User registered successfully: " + userData.getUsername());
    }
}

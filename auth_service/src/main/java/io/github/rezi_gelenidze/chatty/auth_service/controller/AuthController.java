package io.github.rezi_gelenidze.chatty.auth_service.controller;

import io.github.rezi_gelenidze.chatty.auth_service.dto.*;
import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.service.AuthService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        Map<String, String> tokens = authService.login(loginRequest);

        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody @Valid RegisterRequest userData) {
        User createdUser = authService.createUser(userData);

        return ResponseEntity.ok(
                new RegisterResponse(
                        createdUser.getUsername(),
                        createdUser.getEmail(),
                        createdUser.getFirstName(),
                        createdUser.getLastName(),
                        createdUser.getDateOfBirth()
                )
        );
    }
}

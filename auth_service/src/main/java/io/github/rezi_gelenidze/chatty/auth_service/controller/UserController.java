package io.github.rezi_gelenidze.chatty.auth_service.controller;

import io.github.rezi_gelenidze.chatty.auth_service.dto.user.RegisterRequest;
import io.github.rezi_gelenidze.chatty.auth_service.dto.user.RegisterResponse;
import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/auth/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody @Valid RegisterRequest userData) {
        User createdUser = userService.createUser(userData);

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

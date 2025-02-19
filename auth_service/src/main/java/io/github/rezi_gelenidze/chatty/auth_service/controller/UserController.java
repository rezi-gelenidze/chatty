package io.github.rezi_gelenidze.chatty.auth_service.controller;

import io.github.rezi_gelenidze.chatty.auth_service.dto.user.RegisterRequest;
import io.github.rezi_gelenidze.chatty.auth_service.dto.user.RegisterResponse;
import io.github.rezi_gelenidze.chatty.auth_service.entity.Profile;
import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.service.UserService;

import io.github.rezi_gelenidze.chatty.auth_service.util.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody @Valid RegisterRequest userData) {
        User createdUser = userService.createUser(userData);
        Profile createdUserProfile = createdUser.getProfile();

        return ResponseEntity.ok(
                new RegisterResponse(
                        createdUser.getUsername(),
                        createdUser.getEmail(),
                        createdUserProfile.getFirstName(),
                        createdUserProfile.getLastName(),
                        createdUserProfile.getDateOfBirth()
                )
        );
    }

    @GetMapping("/me")
    public ResponseEntity<RegisterResponse> getUser() {
        String username = SecurityUtil.getCurrentUsername();

        User user = userService.getUser(username);
        Profile userProfile = user.getProfile();

        return ResponseEntity.ok(
                new RegisterResponse(
                        user.getUsername(),
                        user.getEmail(),
                        userProfile.getFirstName(),
                        userProfile.getLastName(),
                        userProfile.getDateOfBirth()
                )
        );
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser() {
        String username = SecurityUtil.getCurrentUsername();

        userService.removeUser(username);

        return ResponseEntity.noContent().build();
    }

}

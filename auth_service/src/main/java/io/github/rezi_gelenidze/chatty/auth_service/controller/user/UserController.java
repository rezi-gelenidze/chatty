package io.github.rezi_gelenidze.chatty.auth_service.controller.user;

import io.github.rezi_gelenidze.chatty.auth_service.dto.user.RegisterRequest;
import io.github.rezi_gelenidze.chatty.auth_service.dto.user.UserDetailsResponse;
import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.service.user.UserService;

import io.github.rezi_gelenidze.chatty.auth_service.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "User management endpoints")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user with the provided data")
    @PostMapping
    public ResponseEntity<UserDetailsResponse> registerUser(@RequestBody @Valid RegisterRequest userData) {
        User createdUser = userService.createUser(userData);

        return ResponseEntity.ok(
                UserDetailsResponse.fromEntity(createdUser)
        );
    }

    @Operation(summary = "Get user details", description = "Returns the details of the currently authenticated user")
    @GetMapping("/me")
    public ResponseEntity<UserDetailsResponse> getUser() {
        String username = SecurityUtil.getCurrentUsername();

        User user = userService.getUser(username);

        return ResponseEntity.ok(
                UserDetailsResponse.fromEntity(user)
        );
    }

    @Operation(summary = "Delete user", description = "Deletes the currently authenticated user")
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser() {
        String username = SecurityUtil.getCurrentUsername();

        userService.removeUser(username);

        return ResponseEntity.noContent().build();
    }

}

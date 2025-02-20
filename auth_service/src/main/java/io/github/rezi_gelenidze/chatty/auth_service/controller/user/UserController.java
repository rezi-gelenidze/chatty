package io.github.rezi_gelenidze.chatty.auth_service.controller.user;

import io.github.rezi_gelenidze.chatty.auth_service.dto.user.RegisterRequest;
import io.github.rezi_gelenidze.chatty.auth_service.dto.user.UserDetailsResponse;
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
    public ResponseEntity<UserDetailsResponse> registerUser(@RequestBody @Valid RegisterRequest userData) {
        User createdUser = userService.createUser(userData);

        return ResponseEntity.ok(
                UserDetailsResponse.fromEntity(createdUser)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailsResponse> getUser() {
        String username = SecurityUtil.getCurrentUsername();

        User user = userService.getUser(username);

        return ResponseEntity.ok(
                UserDetailsResponse.fromEntity(user)
        );
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser() {
        String username = SecurityUtil.getCurrentUsername();

        userService.removeUser(username);

        return ResponseEntity.noContent().build();
    }

}

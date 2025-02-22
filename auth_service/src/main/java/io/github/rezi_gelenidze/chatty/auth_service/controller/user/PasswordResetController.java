package io.github.rezi_gelenidze.chatty.auth_service.controller.user;

import io.github.rezi_gelenidze.chatty.auth_service.dto.user.PasswordResetConfirmRequest;
import io.github.rezi_gelenidze.chatty.auth_service.dto.user.PasswordResetRequest;
import io.github.rezi_gelenidze.chatty.auth_service.service.user.PasswordResetService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class PasswordResetController {
    
    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid PasswordResetRequest passwordResetRequest) {
        passwordResetService.sendPasswordResetEmail(passwordResetRequest.getEmail());

        return ResponseEntity.ok("If this email exists, a reset link will be sent.");
    }

    @PostMapping("/reset-password-confirm")
    public ResponseEntity<String> resetPasswordConfirm(@RequestBody @Valid PasswordResetConfirmRequest passwordResetConfirmRequest) {
        passwordResetService.resetPassword(passwordResetConfirmRequest.getToken(), passwordResetConfirmRequest.getPassword());

        return ResponseEntity.ok("Password successfully reset! You can now log in.");
    }
}

package io.github.rezi_gelenidze.chatty.auth_service.controller.user;

import io.github.rezi_gelenidze.chatty.auth_service.service.user.PasswordResetService;
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
    public ResponseEntity<String> resetPassword(@RequestParam String email) {
        passwordResetService.sendPasswordResetEmail(email);

        return ResponseEntity.ok("If this email exists, a reset link will be sent.");
    }

    @PostMapping("/reset-password-confirm")
    public ResponseEntity<String> resetPasswordConfirm(@RequestParam String token, @RequestParam String newPassword) {
        passwordResetService.resetPassword(token, newPassword);

        return ResponseEntity.ok("Password successfully reset! You can now log in.");
    }
}

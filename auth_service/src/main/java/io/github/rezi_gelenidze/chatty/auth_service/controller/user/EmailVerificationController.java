package io.github.rezi_gelenidze.chatty.auth_service.controller.user;

import io.github.rezi_gelenidze.chatty.auth_service.service.user.EmailVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class EmailVerificationController {
    
    private final EmailVerificationService emailVerificationService;

    public EmailVerificationController(EmailVerificationService emailVerificationService) {
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping("/resend-activation")
    public ResponseEntity<String> sendEmailActivation(@RequestParam String email) {
        emailVerificationService.sendEmailVerificationEmail(email);

        return ResponseEntity.ok("If this email exists, a verification link will be sent.");

    }

    @GetMapping("/activation")
    public ResponseEntity<String> activateUser(@RequestParam String token) {
        emailVerificationService.verifyEmail(token);

        return ResponseEntity.ok("Email successfully verified! You can now log in.");
    }
}

package io.github.rezi_gelenidze.chatty.auth_service.controller.user;

import io.github.rezi_gelenidze.chatty.auth_service.dto.user.EmailActivationRequest;
import io.github.rezi_gelenidze.chatty.auth_service.dto.user.EmailResendActivationRequest;
import io.github.rezi_gelenidze.chatty.auth_service.service.user.EmailVerificationService;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> sendEmailActivation(@RequestBody @Valid EmailResendActivationRequest emailResendActivationRequest) {
        emailVerificationService.sendEmailVerificationEmail(emailResendActivationRequest.getEmail());

        return ResponseEntity.ok("If this email exists, a verification link will be sent.");

    }

    @PostMapping("/activation")
    public ResponseEntity<String> activateUser(@RequestBody @Valid EmailActivationRequest emailActivationRequest) {
        emailVerificationService.verifyEmail(emailActivationRequest.getToken());

        return ResponseEntity.ok("Email successfully verified! You can now log in.");
    }
}

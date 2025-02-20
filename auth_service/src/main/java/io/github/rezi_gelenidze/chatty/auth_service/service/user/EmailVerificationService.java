package io.github.rezi_gelenidze.chatty.auth_service.service.user;

import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.exception.UserAlreadyVerifiedException;
import io.github.rezi_gelenidze.chatty.auth_service.exception.UserNotFoundException;
import io.github.rezi_gelenidze.chatty.auth_service.repository.UserRepository;
import io.github.rezi_gelenidze.chatty.auth_service.service.email.EmailService;
import io.github.rezi_gelenidze.chatty.auth_service.service.token.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailVerificationService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Autowired
    public EmailVerificationService(TokenService tokenService, UserRepository userRepository, EmailService emailService) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    /**
     * Sends an email verification email with a one-time HMAC token.
     */
    public void sendEmailVerificationEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        // If the user does not exist, do nothing to prevent user enumeration
        if (optionalUser.isEmpty()) return;

        User user = optionalUser.get();
        String verificationToken = tokenService.generateToken(user, TokenService.TokenType.EMAIL_VERIFICATION);

        emailService.sendActivationEmail(user.getEmail(), verificationToken);
    }

    /**
     * Verifies the token and activates the user.
     */
    public void verifyEmail(String token) {
        // Verify token and extract email
        String email = tokenService.verifyToken(token, TokenService.TokenType.EMAIL_VERIFICATION);


        // Find user by email and activate it
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException("User not found");

        User user = optionalUser.get();
        if (user.getIsActive())
            throw new UserAlreadyVerifiedException("User is already verified");

        user.setIsActive(true);
        userRepository.save(user);

        // Send email verification success message
        emailService.sendActivationSuccessEmail(email);
    }
}

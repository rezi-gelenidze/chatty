package io.github.rezi_gelenidze.chatty.auth_service.service.user;

import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.exception.UserNotFoundException;
import io.github.rezi_gelenidze.chatty.auth_service.repository.UserRepository;
import io.github.rezi_gelenidze.chatty.auth_service.service.email.EmailService;
import io.github.rezi_gelenidze.chatty.auth_service.service.token.TokenService;
import io.github.rezi_gelenidze.chatty.auth_service.service.token.TokenService.TokenType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetService {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(TokenService tokenService, UserRepository userRepository,
                                EmailService emailService, PasswordEncoder passwordEncoder) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Sends a password reset email with a one-time HMAC token.
     */
    public void sendPasswordResetEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        // If the user does not exist, do nothing to prevent user enumeration
        if (optionalUser.isEmpty()) return;

        User user = optionalUser.get();
        String resetToken = tokenService.generateToken(user, TokenType.PASSWORD_RESET);

        emailService.sendPasswordResetEmail(user.getEmail(), resetToken, user.getUsername());
    }

    /**
     * Verifies the password reset token and updates the user's password.
     */
    public void resetPassword(String token, String newPassword) {
        String email = tokenService.verifyToken(token, TokenType.PASSWORD_RESET);

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException("User not found");

        User user = optionalUser.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        emailService.sendPasswordResetSuccessEmail(user.getEmail());
    }
}

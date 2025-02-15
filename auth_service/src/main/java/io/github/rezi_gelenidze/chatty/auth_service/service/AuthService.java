package io.github.rezi_gelenidze.chatty.auth_service.service;

import io.github.rezi_gelenidze.chatty.auth_service.dto.LoginRequest;
import io.github.rezi_gelenidze.chatty.auth_service.dto.RegisterRequest;
import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.exception.InvalidCredentialsException;
import io.github.rezi_gelenidze.chatty.auth_service.exception.UserNotFoundException;
import io.github.rezi_gelenidze.chatty.auth_service.repository.UserRepository;
import io.github.rezi_gelenidze.chatty.auth_service.util.JwtUtil;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public boolean validateUser(String username, String rawPassword) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Compare the provided password with the hashed password
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }


    @Transactional
    public User createUser(RegisterRequest registerRequest) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(registerRequest.getUsername()))
            throw new IllegalArgumentException("Username is already taken");

        if (userRepository.existsByEmail(registerRequest.getEmail()))
            throw new IllegalArgumentException("Email is already registered");

        // Create new user instance
        User user = new User();

        // Map RegisterRequest fields to User entity
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setDateOfBirth(registerRequest.getDateOfBirth());

        // Hash the password
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Save and return the new user
        return userRepository.save(user);
    }

    public Map<String, String> login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        // Authenticate user
        boolean isAuthenticated = this.validateUser(username, password);
        if (!isAuthenticated)
            throw new InvalidCredentialsException("Invalid credentials");

        // Generate JWT tokens
        String accessToken = jwtUtil.generateToken(username);
        String refreshToken = jwtUtil.generateRefreshToken(username);

        // Return tokens as a response
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }
}

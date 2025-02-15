package io.github.rezi_gelenidze.chatty.auth_service.service;

import io.github.rezi_gelenidze.chatty.auth_service.dto.RegisterRequest;
import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void createUser(RegisterRequest registerRequest) {
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
        userRepository.save(user);
    }
}
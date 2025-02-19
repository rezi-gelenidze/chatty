package io.github.rezi_gelenidze.chatty.auth_service.service;

import io.github.rezi_gelenidze.chatty.auth_service.dto.user.*;
import io.github.rezi_gelenidze.chatty.auth_service.entity.Profile;
import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.repository.UserRepository;
import io.github.rezi_gelenidze.chatty.auth_service.exception.UserNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public boolean validateUser(String username, String rawPassword) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));

        // Compare the provided password with the hashed password
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    @Transactional
    public User createUser(RegisterRequest registerRequest) {
        User user = new User();
        Profile profile = new Profile();

        // Map RegisterRequest fields to User entity
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());

        // Map RegisterRequest fields to Profile entity
        profile.setFirstName(registerRequest.getFirstName());
        profile.setLastName(registerRequest.getLastName());
        profile.setDateOfBirth(registerRequest.getDateOfBirth());

        // Set the user and profile references
        user.setProfile(profile);
        profile.setUser(user);

        // Hash the password
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // Save and return the new user and profile
        return userRepository.save(user);
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void removeUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }
}

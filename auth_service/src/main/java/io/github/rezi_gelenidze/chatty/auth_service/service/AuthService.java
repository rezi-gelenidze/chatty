package io.github.rezi_gelenidze.chatty.auth_service.service;

import io.github.rezi_gelenidze.chatty.auth_service.entity.User;
import io.github.rezi_gelenidze.chatty.auth_service.exception.UserNotFoundException;
import io.github.rezi_gelenidze.chatty.auth_service.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public boolean validateUser(String username, String rawPassword) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Compare the provided password with the hashed password
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}

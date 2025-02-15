package io.github.rezi_gelenidze.chatty.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import io.github.rezi_gelenidze.chatty.auth_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}

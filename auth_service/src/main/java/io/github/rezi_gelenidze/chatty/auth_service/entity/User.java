package io.github.rezi_gelenidze.chatty.auth_service.entity;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;
import io.github.rezi_gelenidze.chatty.auth_service.entity.Profile;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 255)
    @Pattern(regexp = ValidationConstants.USERNAME_REGEX)
    private String username;

    @Column(unique = true, nullable = false, length = 255)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "is_banned", nullable = false)
    private Boolean isBanned = false;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Profile profile;
}

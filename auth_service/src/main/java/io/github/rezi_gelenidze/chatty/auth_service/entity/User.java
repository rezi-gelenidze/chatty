package io.github.rezi_gelenidze.chatty.auth_service.entity;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"user\"", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 255)
    @Pattern(regexp = ValidationConstants.USERNAME_REGEX)
    private String username;

    @Column(unique = true, nullable = false, length = 255)
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false, length = 255)
    @Pattern(regexp = ValidationConstants.NAME_REGEX, message = ValidationConstants.NAME_REGEX_MESSAGE)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    @Pattern(regexp = ValidationConstants.NAME_REGEX, message = ValidationConstants.NAME_REGEX_MESSAGE)
    private String lastName;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Column(name = "is_banned", nullable = false)
    private Boolean isBanned = false;

    @Column(name = "is_admin", nullable = false)
    private Boolean isAdmin = false;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}

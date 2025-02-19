package io.github.rezi_gelenidze.chatty.auth_service.entity;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "first_name", nullable = false, length = 255)
    @Pattern(regexp = ValidationConstants.NAME_REGEX, message = ValidationConstants.NAME_REGEX_MESSAGE)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 255)
    @Pattern(regexp = ValidationConstants.NAME_REGEX, message = ValidationConstants.NAME_REGEX_MESSAGE)
    private String lastName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "profile_picture", columnDefinition = "TEXT", nullable = true)
    private String profilePicture;

    @Column(name = "bio", length = 255, nullable = true)
    private String bio;
}

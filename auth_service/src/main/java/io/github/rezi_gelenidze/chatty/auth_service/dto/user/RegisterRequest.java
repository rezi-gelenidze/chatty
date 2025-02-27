package io.github.rezi_gelenidze.chatty.auth_service.dto.user;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;

import io.github.rezi_gelenidze.chatty.auth_service.validation.annotation.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@PasswordMatch(message = ValidationConstants.PASSWORD_MISMATCH_MESSAGE)
public class RegisterRequest {
    @ValidUsername
    @UniqueUsername
    private String username;

    @ValidEmail
    @UniqueEmail
    private String email;

    @ValidPassword
    private String password;

    @NotBlank(message = ValidationConstants.PASSWORD_REPEAT_REQUIRED_MESSAGE)
    private String passwordRepeat;

    @ValidName
    private String firstName;

    @ValidName
    private String lastName;

    @ValidDateOfBirth
    private LocalDate dateOfBirth;
}
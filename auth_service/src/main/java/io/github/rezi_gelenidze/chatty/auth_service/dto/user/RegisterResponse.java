package io.github.rezi_gelenidze.chatty.auth_service.dto.user;

import java.time.LocalDate;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
    private String username;

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;
}
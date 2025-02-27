package io.github.rezi_gelenidze.chatty.auth_service.dto.jwt;

import io.github.rezi_gelenidze.chatty.auth_service.validation.annotation.ValidPassword;
import io.github.rezi_gelenidze.chatty.auth_service.validation.annotation.ValidUsername;
import lombok.Data;

@Data
public class LoginRequest {
    @ValidUsername
    private String username;

    @ValidPassword
    private String password;
}

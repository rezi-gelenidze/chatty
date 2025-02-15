package io.github.rezi_gelenidze.chatty.auth_service.dto;

import lombok.Data;

import io.github.rezi_gelenidze.chatty.auth_service.validation.*;

@Data
public class LoginRequest {
    @ValidUsername
    private String username;

    @ValidPassword
    private String password;
}

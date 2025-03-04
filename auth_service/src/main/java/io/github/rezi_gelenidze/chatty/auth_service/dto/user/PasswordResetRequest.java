package io.github.rezi_gelenidze.chatty.auth_service.dto.user;

import io.github.rezi_gelenidze.chatty.auth_service.validation.annotation.ValidEmail;
import lombok.Data;


@Data
public class PasswordResetRequest {
    @ValidEmail
    private String email;
}

package io.github.rezi_gelenidze.chatty.auth_service.dto.user;

import io.github.rezi_gelenidze.chatty.auth_service.validation.annotation.ValidPassword;
import lombok.Data;


@Data
public class PasswordResetConfirmRequest {
    private String token;

    @ValidPassword
    private String newPassword;
}

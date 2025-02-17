package io.github.rezi_gelenidze.chatty.auth_service.dto.jwt;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequest {
    @NotBlank(message = ValidationConstants.REFRESH_TOKEN_REQUIRED_MESSAGE)
    private String refreshToken;
}

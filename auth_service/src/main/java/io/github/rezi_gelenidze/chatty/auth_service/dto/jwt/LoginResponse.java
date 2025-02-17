package io.github.rezi_gelenidze.chatty.auth_service.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String accessToken;

    private String refreshToken;
}

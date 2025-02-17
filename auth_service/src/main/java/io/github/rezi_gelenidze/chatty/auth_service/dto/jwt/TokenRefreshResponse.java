package io.github.rezi_gelenidze.chatty.auth_service.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenRefreshResponse {
    private String accessToken;
}

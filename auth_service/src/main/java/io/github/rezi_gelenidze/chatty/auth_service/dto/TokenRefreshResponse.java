package io.github.rezi_gelenidze.chatty.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class TokenRefreshResponse {
    private String accessToken;
}

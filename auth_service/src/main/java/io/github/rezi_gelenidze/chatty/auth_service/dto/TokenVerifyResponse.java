package io.github.rezi_gelenidze.chatty.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenVerifyResponse {
    private String username;
}

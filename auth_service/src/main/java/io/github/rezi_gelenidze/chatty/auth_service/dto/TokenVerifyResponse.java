package io.github.rezi_gelenidze.chatty.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

@Getter
@AllArgsConstructor
public class TokenVerifyResponse {
    private String username;
}

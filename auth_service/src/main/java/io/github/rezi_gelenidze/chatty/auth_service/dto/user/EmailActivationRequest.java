package io.github.rezi_gelenidze.chatty.auth_service.dto.user;

import lombok.Data;


@Data
public class EmailActivationRequest {
    private String token;
}

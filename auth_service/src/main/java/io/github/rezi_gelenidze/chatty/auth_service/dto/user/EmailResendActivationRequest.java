package io.github.rezi_gelenidze.chatty.auth_service.dto.user;

import io.github.rezi_gelenidze.chatty.auth_service.validation.ValidEmail;
import lombok.Data;


@Data
public class EmailResendActivationRequest {
    @ValidEmail
    private String email;
}

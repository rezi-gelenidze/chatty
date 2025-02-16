package io.github.rezi_gelenidze.chatty.auth_service.exception;

import org.springframework.http.HttpStatus;

public class TokenInvalidException extends ApiException {
    public TokenInvalidException(String message) {
        super("TOKEN_INVALID", message, HttpStatus.UNAUTHORIZED);
    }
}

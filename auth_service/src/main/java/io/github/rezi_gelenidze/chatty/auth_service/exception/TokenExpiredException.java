package io.github.rezi_gelenidze.chatty.auth_service.exception;

import org.springframework.http.HttpStatus;

public class TokenExpiredException extends ApiException {
    public TokenExpiredException(String message) {
        super("TOKEN_EXPIRED", message, HttpStatus.UNAUTHORIZED);
    }
}

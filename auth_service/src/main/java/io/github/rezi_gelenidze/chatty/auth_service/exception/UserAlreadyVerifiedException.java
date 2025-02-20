package io.github.rezi_gelenidze.chatty.auth_service.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyVerifiedException extends ApiException {
    public UserAlreadyVerifiedException(String message) {
        super("USER_ALREADY_VERIFIED", message, HttpStatus.BAD_REQUEST);
    }
}

package io.github.rezi_gelenidze.chatty.auth_service.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException(String message) {
        super("USER_NOT_FOUND", message, HttpStatus.NOT_FOUND);
    }
}

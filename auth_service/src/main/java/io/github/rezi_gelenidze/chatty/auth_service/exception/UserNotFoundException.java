package io.github.rezi_gelenidze.chatty.auth_service.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

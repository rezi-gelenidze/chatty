package io.github.rezi_gelenidze.chatty.auth_service.validation.validator;

import io.github.rezi_gelenidze.chatty.auth_service.dto.user.RegisterRequest;
import io.github.rezi_gelenidze.chatty.auth_service.validation.annotation.PasswordMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, RegisterRequest> {

    @Override
    public boolean isValid(RegisterRequest request, ConstraintValidatorContext context) {
        return request.getPassword() != null && request.getPassword().equals(request.getPasswordRepeat());
    }
}
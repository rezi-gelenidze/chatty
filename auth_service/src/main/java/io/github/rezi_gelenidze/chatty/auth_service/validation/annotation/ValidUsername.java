package io.github.rezi_gelenidze.chatty.auth_service.validation.annotation;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {}) // No explicit validator needed
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = ValidationConstants.USERNAME_REQUIRED_MESSAGE)
@Pattern(regexp = ValidationConstants.USERNAME_REGEX, message = ValidationConstants.USERNAME_REGEX_MESSAGE)
@Size(min = ValidationConstants.USERNAME_MIN_LENGTH, max = ValidationConstants.USERNAME_MAX_LENGTH, message = ValidationConstants.USERNAME_LENGTH_MESSAGE)
public @interface ValidUsername {
    String message() default "Invalid username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

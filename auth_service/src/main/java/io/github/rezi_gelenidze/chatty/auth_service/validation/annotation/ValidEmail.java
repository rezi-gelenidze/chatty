package io.github.rezi_gelenidze.chatty.auth_service.validation.annotation;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {}) // No explicit validator needed
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = ValidationConstants.EMAIL_REQUIRED_MESSAGE)
@Email(message = ValidationConstants.EMAIL_INVALID_MESSAGE)
@Size(max = ValidationConstants.EMAIL_MAX_LENGTH, message = ValidationConstants.EMAIL_LENGTH_MESSAGE)
public @interface ValidEmail {
    String message() default "Invalid email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

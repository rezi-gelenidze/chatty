package io.github.rezi_gelenidze.chatty.auth_service.validation.annotation;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = ValidationConstants.PASSWORD_REQUIRED_MESSAGE)
@Size(min = ValidationConstants.PASSWORD_MIN_LENGTH, max = ValidationConstants.PASSWORD_MAX_LENGTH, message = ValidationConstants.PASSWORD_LENGTH_MESSAGE)
@Pattern(regexp = ValidationConstants.PASSWORD_REGEX, message = ValidationConstants.PASSWORD_REGEX_MESSAGE)
public @interface ValidPassword {
    String message() default "Invalid password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

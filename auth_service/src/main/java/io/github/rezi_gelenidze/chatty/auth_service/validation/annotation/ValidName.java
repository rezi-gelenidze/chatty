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
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = ValidationConstants.NAME_REQUIRED_MESSAGE)
@Size(min = ValidationConstants.NAME_MIN_LENGTH, 
      max = ValidationConstants.NAME_MAX_LENGTH, 
      message = ValidationConstants.NAME_LENGTH_MESSAGE)
@Pattern(regexp = ValidationConstants.NAME_REGEX, 
         message = ValidationConstants.NAME_REGEX_MESSAGE)
public @interface ValidName {
    String message() default "Invalid name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

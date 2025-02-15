package io.github.rezi_gelenidze.chatty.auth_service.validation;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.lang.annotation.*;
import java.time.LocalDate;
import java.time.Period;

@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = ValidationConstants.DOB_REQUIRED_MESSAGE)
@Past(message = ValidationConstants.DOB_PAST_MESSAGE)
public @interface ValidDateOfBirth {
    String message() default ValidationConstants.DOB_AGE_MESSAGE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

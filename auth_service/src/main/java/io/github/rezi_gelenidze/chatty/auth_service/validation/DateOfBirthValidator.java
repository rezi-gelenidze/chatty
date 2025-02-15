package io.github.rezi_gelenidze.chatty.auth_service.validation;

import io.github.rezi_gelenidze.chatty.auth_service.constants.ValidationConstants;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

public class DateOfBirthValidator implements ConstraintValidator<ValidDateOfBirth, LocalDate> {
    private static final int MIN_AGE = ValidationConstants.DOB_MIN_AGE;

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
        if (dob == null) {
            return false; // @NotNull handles this case separately
        }
        return Period.between(dob, LocalDate.now()).getYears() >= MIN_AGE;
    }
}

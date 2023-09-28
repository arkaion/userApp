package com.example.demo.utility.Validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AdultValidator implements ConstraintValidator<Adult, Date> {
    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values are handled by @NotNull annotation
        }

        LocalDate birthdate = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);

        return birthdate.isBefore(eighteenYearsAgo);
    }
}

package com.example.demo.utility.Validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AdultValidator implements ConstraintValidator<Adult, String> {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Null values, handled by @NotBlank annotation
        }

        try {
            LocalDate birthdate = LocalDate.parse(value, DATE_FORMATTER);
            LocalDate eighteenYearsAgo = LocalDate.now().minusYears(18);

            return birthdate.isBefore(eighteenYearsAgo);
        } catch (DateTimeParseException e) {
            return true; // Invalid date format, handled by the @Date validator
        }
    }
}

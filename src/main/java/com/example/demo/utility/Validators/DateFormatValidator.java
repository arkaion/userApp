package com.example.demo.utility.Validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Null and blank values are handled by @NotBlank annotation
        }

        try {
            formatter.parse(value);
            return true;
        } catch (ParseException e) {
            return false; // Invalid date format
        }
    }
}

package com.example.demo.utility.Validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FrenchValidator implements ConstraintValidator<French, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Null and blank values are handled by @NotBlank annotation
        }
        // Validate that the country is "France" (case-insensitive)
        return value.equalsIgnoreCase("France");
    }
}

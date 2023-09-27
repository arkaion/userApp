package com.example.demo.utility.Validators;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FrenchValidator implements ConstraintValidator<French, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Validate that the country is "France" (case-insensitive)
        return value != null && value.equalsIgnoreCase("France");
    }
}

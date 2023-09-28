package com.example.demo.utility.Validators;

import com.example.demo.utility.Enums.GenderEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class GenderValidator implements ConstraintValidator<Gender, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return true; // Empty or null values are allowed
        }

        for (GenderEnum gender : GenderEnum.values()) {
            if (gender.name().equalsIgnoreCase(value)) {
                return true; // Match found
            }
        }

        return false; // No match found
    }
}

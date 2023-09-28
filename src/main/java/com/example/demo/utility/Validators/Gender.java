package com.example.demo.utility.Validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = GenderValidator.class)
@Documented
public @interface Gender {
    String message() default "User gender must be valid (Male, Female, Other or Prefer_not_to_say)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

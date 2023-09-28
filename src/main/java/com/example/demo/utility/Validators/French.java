package com.example.demo.utility.Validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FrenchValidator.class)
@Documented
public @interface French {
    String message() default "User must be French.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}


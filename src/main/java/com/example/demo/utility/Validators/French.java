package com.example.demo.utility.Validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FrenchValidator.class)
@Documented
public @interface French {
    String message() default "User must be French.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}


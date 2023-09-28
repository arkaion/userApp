package com.example.demo.utility.Validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateFormatValidator.class)
@Documented
public @interface DateFormat {
    String message() default "User birthdate must be valid (yyyy-MM-dd format)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

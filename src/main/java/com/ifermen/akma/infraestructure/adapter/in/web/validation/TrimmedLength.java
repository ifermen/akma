package com.ifermen.akma.infraestructure.adapter.in.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TrimmedLength.List.class)
@Constraint(validatedBy = TrimmedLengthValidator.class)
public @interface TrimmedLength {
    String message() default "Invalid length";
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        TrimmedLength[] value();
    }
}

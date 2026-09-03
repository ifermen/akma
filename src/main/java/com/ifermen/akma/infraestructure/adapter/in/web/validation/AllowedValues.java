package com.ifermen.akma.infraestructure.adapter.in.web.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AllowedValuesValidator.class)
public @interface AllowedValues {
    String message() default "must be one of the allowed values";
    String[] values(); // la lista de valores permitidos
    boolean ignoreCase() default false;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

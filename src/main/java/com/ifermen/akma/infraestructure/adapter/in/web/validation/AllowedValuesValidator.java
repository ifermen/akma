package com.ifermen.akma.infraestructure.adapter.in.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class AllowedValuesValidator  implements ConstraintValidator<AllowedValues, String> {

    private String[] allowedValues;
    private boolean ignoreCase;

    @Override
    public void initialize(AllowedValues constraintAnnotation) {
        this.allowedValues = constraintAnnotation.values();
        this.ignoreCase = constraintAnnotation.ignoreCase();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Arrays.stream(allowedValues)
                .anyMatch(allowed -> ignoreCase ? allowed.equalsIgnoreCase(value) : allowed.equals(value));
    }
}

package com.ifermen.akma.infraestructure.adapter.in.web.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TrimmedLengthValidator implements ConstraintValidator<TrimmedLength, String> {

    private int min;
    private int max;

    @Override
    public void initialize(TrimmedLength constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        String trimmed = value.trim();
        if (trimmed.isEmpty()) {
            return true;
        }
        return trimmed.length() >= min && trimmed.length() <= max;
    }
}

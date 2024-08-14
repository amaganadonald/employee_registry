package com.amagana.technicaltest.employeemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class DepartmentNameValidator implements ConstraintValidator<ValidateDepartmentName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String[] word = value.trim().split("");
        return Arrays.stream(word)
                .allMatch(str->str.matches("^([A-Z])([a-zA-Z0-9])*"));
    }
}

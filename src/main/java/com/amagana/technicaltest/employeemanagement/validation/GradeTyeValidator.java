package com.amagana.technicaltest.employeemanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class GradeTyeValidator implements ConstraintValidator<ValidateGradeType, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<String> list = Arrays.asList("MALE", "FEMALE");
        return list.contains(value);
    }
}

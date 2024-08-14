package com.amagana.technicaltest.employeemanagement.validation;

import com.amagana.technicaltest.employeemanagement.entity.Employee;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmployeeStartDateBeforeEndDateValidator implements ConstraintValidator<ValidateStartDateBeforeEndDate, Object> {
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof Employee e) {
            return e.getContractStartDate().isBefore(e.getContractEndDate());
        }
        return false;
    }
}

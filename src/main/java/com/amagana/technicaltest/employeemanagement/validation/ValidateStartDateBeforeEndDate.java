package com.amagana.technicaltest.employeemanagement.validation;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Constraint(validatedBy = EmployeeStartDateBeforeEndDateValidator.class)
public @interface ValidateStartDateBeforeEndDate {
    public String message() default "Validated Date Constrains failed Start Date must be before End Date";
    Class<?>[] groups() default {};
    Class<? extends java.lang.annotation.ElementType>[] payload() default {};
}

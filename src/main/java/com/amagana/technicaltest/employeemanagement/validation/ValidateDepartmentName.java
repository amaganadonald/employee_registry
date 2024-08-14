package com.amagana.technicaltest.employeemanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Constraint(validatedBy = DepartmentNameValidator.class)
public @interface ValidateDepartmentName {
    public String message() default "Each word in the department name must be in capital letters";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

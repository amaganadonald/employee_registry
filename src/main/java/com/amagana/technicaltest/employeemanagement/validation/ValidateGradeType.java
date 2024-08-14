package com.amagana.technicaltest.employeemanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = GradeTyeValidator.class)
public @interface ValidateGradeType {
    public String message() default "Invalid Gender It Should be MALE or FEMALE in Capital Letter";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

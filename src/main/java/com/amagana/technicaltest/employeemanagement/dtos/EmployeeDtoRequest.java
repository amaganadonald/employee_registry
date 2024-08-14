package com.amagana.technicaltest.employeemanagement.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.amagana.technicaltest.employeemanagement.service.IEmployeeByFirstname;
import com.amagana.technicaltest.employeemanagement.service.IEmployeeByLastName;
import com.amagana.technicaltest.employeemanagement.validation.ValidateGradeType;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record EmployeeDtoRequest(Long id,
                                 @NotEmpty(message = "Firstname must not be empty")
                                 @NotBlank(groups = {IEmployeeByFirstname.class}, message = "Firstname mustn't be blank")
                                 @NotNull(message = "Firstname mustn't be null")
                                 String firstName,
                                 @NotEmpty(message = "LastName must not be empty")
                                 @NotBlank(groups = {IEmployeeByLastName.class}, message = "LastName mustn't be blank")
                                 @NotNull(message = "LastName is mandatory")
                                 String lastName,
                                 @Pattern(regexp = "^(?:[A-Z][a-z]*\\s?)+$", message = "Each word must begin with capital letter")
                                 String jobTitle,
                                 @JsonProperty(defaultValue = "Donald",  access = JsonProperty.Access.READ_ONLY)
                                 String createdUserName,
                                 @NotNull(message = "contractStartDate Start date is mandatory")
                                 LocalDate contractStartDate,
                                 LocalDate contractEndDate,
                                 @ValidateGradeType
                                 String gender,
                                 Long managerId, Long departmentId, Long gradeId) {
}

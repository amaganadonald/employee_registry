package com.amagana.technicaltest.employeemanagement.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.amagana.technicaltest.employeemanagement.entity.Department;
import com.amagana.technicaltest.employeemanagement.entity.Grade;
import com.amagana.technicaltest.employeemanagement.enums.Gender;
import lombok.Builder;

import java.time.LocalDate;

@Builder
@JsonPropertyOrder(value = {"id", "firstName", "lastName", "jobTitle", "gender", "contractStartDate"})
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public record EmployeeDtoResponse(Long id, String firstName, String lastName,
                                  String jobTitle, LocalDate contractStartDate,
                                  LocalDate contractEndDate, Gender gender,
                                  Department department,
                                  Grade grade
                                  ) {
}

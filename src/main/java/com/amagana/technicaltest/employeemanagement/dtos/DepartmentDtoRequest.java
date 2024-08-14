package com.amagana.technicaltest.employeemanagement.dtos;

import com.amagana.technicaltest.employeemanagement.validation.ValidateDepartmentName;
import lombok.Builder;

@Builder
public record DepartmentDtoRequest(Long id, @ValidateDepartmentName String name) {
}

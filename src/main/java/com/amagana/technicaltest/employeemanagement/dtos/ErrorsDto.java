package com.amagana.technicaltest.employeemanagement.dtos;

import lombok.Builder;

@Builder
public record ErrorsDto(String field, String message) {
}

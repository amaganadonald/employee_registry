package com.amagana.technicaltest.employeemanagement.dtos;

import lombok.Builder;

import java.util.Date;

@Builder
public record GradeDtoRequest(Long id, String gradeName, Date fromDate, Date toDate) {
}

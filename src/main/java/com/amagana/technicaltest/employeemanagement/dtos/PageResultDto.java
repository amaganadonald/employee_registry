package com.amagana.technicaltest.employeemanagement.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record PageResultDto<T>(int totalPages, long totalElements, int size,
                               boolean first, boolean last,
                               int number,
                               List<T> content) {
}

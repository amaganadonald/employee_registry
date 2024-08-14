package com.amagana.technicaltest.employeemanagement.dtos;

import java.time.LocalDate;

public record SearchDto(String firstName, String lastName, String jobTitle, String name,
                        LocalDate startDate, LocalDate endDate) {
}

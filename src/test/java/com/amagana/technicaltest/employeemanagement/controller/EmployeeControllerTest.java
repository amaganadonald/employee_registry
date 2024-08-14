package com.amagana.technicaltest.employeemanagement.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoResponse;
import com.amagana.technicaltest.employeemanagement.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @MockBean
    EmployeeService employeeService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    EmployeeDtoRequest employeeDtoRequest;
    EmployeeDtoResponse employeeDtoResponse;

    @BeforeEach
    void setUp() {
        employeeDtoRequest = EmployeeDtoRequest.builder()
                .id(1L)
                .firstName("John Doe")
                .lastName("Smith")
                .jobTitle("Developer Java")
                .contractEndDate(LocalDate.now())
                .contractStartDate(LocalDate.now())
                .build();
        employeeDtoResponse = EmployeeDtoResponse.builder()
                .id(1L)
                .firstName("John Doe")
                .lastName("Smith")
                .jobTitle("developer Java")
                .contractStartDate(LocalDate.now())
                .contractEndDate(LocalDate.now())
                .build();
    }
    @Test
    void shouldCreateNewEmployee() throws Exception {
        when(employeeService.createEmployee(employeeDtoRequest)).thenReturn(employeeDtoResponse);
        mockMvc.perform(post("/api/v1/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDtoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusResponse").value("SUCCESS"))
                .andExpect(jsonPath("$.results.firstName").value("John Doe"));
    }
}

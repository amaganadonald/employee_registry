package com.amagana.technicaltest.employeemanagement.service;

import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoResponse;
import com.amagana.technicaltest.employeemanagement.entity.Employee;
import com.amagana.technicaltest.employeemanagement.enums.Gender;
import com.amagana.technicaltest.employeemanagement.repository.EmployeeRepository;
import com.amagana.technicaltest.employeemanagement.service.impl.DepartmentServiceImpl;
import com.amagana.technicaltest.employeemanagement.service.impl.EmployeeServiceImpl;
import com.amagana.technicaltest.employeemanagement.service.impl.GradeServiceImpl;
import com.amagana.technicaltest.employeemanagement.utilis.EmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @InjectMocks
    private GradeServiceImpl gradeService;

    private Employee employee;
    private EmployeeDtoResponse employeeDtoResponse;
    private EmployeeDtoRequest employeeDtoRequest;

    @BeforeEach
    void setUp() {
        employeeDtoRequest = EmployeeDtoRequest.builder()
               .id(1L)
               .firstName("Donald")
               .lastName("Doe")
               .jobTitle("Java Developer")
                .departmentId(0L)
                .gradeId(0L)
                .gender("FEMALE")
                .managerId(0L)
                .contractEndDate(LocalDate.now())
               .build();
        employee = Employee.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .jobTitle("Java Developer")
                .department(null)
                .grade(null)
                .gender(Gender.FEMALE)
                .contractEndDate(LocalDate.now())
                .build();
        employeeDtoResponse = EmployeeDtoResponse.builder()
                .id(1L)
                .firstName("Donald")
                .lastName("Doe")
                .jobTitle("Java Developer")
                .department(null)
                .grade(null)
                .gender(Gender.FEMALE)
                .contractEndDate(LocalDate.now())
                .build();
    }

    @Test
    void should_CreateNewEmployeeThenReturnEmployeeCreated(){
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDtoResponse employee1 = employeeService.createEmployee(employeeDtoRequest);
        assertEquals(1L, employee1.id());
        assertNotNull(employee1);
    }

    @Test
    void givenIdFindAndReturnEmployeeById() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));
        EmployeeDtoResponse employee1 = employeeService.getEmployeeById(1L);
        assertEquals(1L, employee1.id());
        assertNotNull(employee1);
    }

    @Test
    void should_UpdateEmployeeThenReturnEmployeeUpdated(){

        when(employeeRepository.findById(1L)).thenReturn(Optional.ofNullable(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
        EmployeeDtoResponse employee1 = employeeService.updateEmployee(1L, employeeDtoRequest);
        assertEquals("Donald", employee1.firstName());
    }
}

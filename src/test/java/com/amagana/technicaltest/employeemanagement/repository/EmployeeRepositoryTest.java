package com.amagana.technicaltest.employeemanagement.repository;

import com.amagana.technicaltest.employeemanagement.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.class)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    Employee employee;

    @BeforeEach
    void setUp() {
        employee = Employee.builder()
                .jobTitle("IT")
                .lastName("Donald")
                .firstName("Evrard")
                .contractEndDate(LocalDate.now())
                .build();
    }

    @Test
    @Order(1)
    @Rollback(false)
    void givenEmployee_saveEmployee_ThenReturnSaveEmployee() {
        Employee employees = employeeRepository.save(employee);
        assertNotNull(employees);
        assertEquals(1, employees.getId());
    }

    @Test
    @Order(2)
    @Rollback(false)
    void givenIdAndEmployee_findAndUpdateEmployeeById_ThenReturnEmployeeUpdated() {
        Employee employee1 = employeeRepository.findById(1L).orElseThrow();
        employee1.setJobTitle("IT Manager");
        assertNotNull(employee1);
        assertEquals("IT Manager", employee1.getJobTitle());
    }

    @Test
    @Order(3)
    @Rollback(false)
    void givenId_findEmployeeById_ThenReturnEmployeeFound() {
        Employee employee1 = employeeRepository.findById(1L).orElseThrow();
        assertNotNull(employee1);
        assertEquals("IT Manager", employee1.getJobTitle());
    }

    @Test
    @Order(4)
    @Rollback(false)
    void givenId_findEmployeeById_ThenReturnNull() {
        Employee employee1 = employeeRepository.findById(2L).orElse(null);
        assertNull(employee1);
    }
}

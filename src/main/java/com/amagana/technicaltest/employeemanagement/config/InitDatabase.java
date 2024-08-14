package com.amagana.technicaltest.employeemanagement.config;

import com.amagana.technicaltest.employeemanagement.dtos.DepartmentDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.GradeDtoRequest;
import com.amagana.technicaltest.employeemanagement.service.DepartmentService;
import com.amagana.technicaltest.employeemanagement.service.EmployeeService;
import com.amagana.technicaltest.employeemanagement.service.GradeService;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//@Configuration
public class InitDatabase {

    //@Bean
    CommandLineRunner commandLineRunner(GradeService gradeService, DepartmentService departmentService,
                                        EmployeeService employeeService) {
        return  args -> {
            List<GradeDtoRequest> gradeDtoRequests = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {
                Faker faker = new Faker(new Locale("en", "US"));
                GradeDtoRequest gradeDtoRequest = GradeDtoRequest.builder()
                        .gradeName(faker.company().profession())
                        .fromDate(new Date(faker.number().numberBetween(1980, 2000), faker.number().numberBetween(1, 12),
                                faker.number().numberBetween(1, 28)))
                        .toDate(new Date(faker.number().numberBetween(2000, 2024), faker.number().numberBetween(1, 12),
                                faker.number().numberBetween(1, 28)))
                        .build();
                gradeDtoRequests.add(gradeDtoRequest);
            }
            gradeService.createListGrade(gradeDtoRequests);
            List<DepartmentDtoRequest> departmentDtoRequests = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                Faker faker = new Faker();
                DepartmentDtoRequest departmentDtoRequest = DepartmentDtoRequest.builder()
                        .name(faker.company().industry())
                       .build();
                departmentDtoRequests.add(departmentDtoRequest);
            }
            departmentService.createListOfDepartment(departmentDtoRequests);
            int manager = 0;
            for (int i = 0; i < 500; i++) {
                Faker faker = new Faker();
                if (i > 20 && manager < 20) {
                    manager = i-1;
                }
                EmployeeDtoRequest employeeDtoRequest = EmployeeDtoRequest.builder()
                        .contractStartDate(LocalDate.of(faker.number().numberBetween(1980, 2010), faker.number().numberBetween(1,12),
                                faker.number().numberBetween(1, 28)))
                        .contractEndDate(LocalDate.of(faker.number().numberBetween(2010, 2024), faker.number().numberBetween(1, 12),
                                faker.number().numberBetween(1, 28)))
                        .firstName(faker.name().firstName())
                        .jobTitle(faker.job().position())
                        .lastName(faker.name().lastName())
                        .gender(faker.options().option("MALE", "FEMALE"))
                        .departmentId((long) faker.number().numberBetween(1, 100))
                        .gradeId((long) faker.number().numberBetween(1, 1000))
                        .managerId((long) faker.number().numberBetween(0, manager))
                        .build();
                employeeService.createEmployee(employeeDtoRequest);
            }

        };
    }

}

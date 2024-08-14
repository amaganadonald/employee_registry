package com.amagana.technicaltest.employeemanagement.config;

import com.amagana.technicaltest.employeemanagement.entity.Employee;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

@UtilityClass
public class SpecificationSearch {

    public Specification<Employee> searchByFirstName(String firstName) {
        return (root, query, builder) -> {
            if(firstName.isEmpty() || firstName.isBlank())
                return null;
            return builder.like(root.get("firstName"), "%"+ firstName + "%");
        };
    }
    public Specification<Employee> searchByLastName(String lastName) {
        return (root, query, builder) -> {
            if(lastName.isEmpty() || lastName.isBlank())
                return null;
            return builder.like(root.get("lastName"), "%"+ lastName.toLowerCase() + "%");
        };
    }
    public Specification<Employee> searchByJobTitle(String jobTitle) {
        return (root, query, builder) -> {
            if(jobTitle.isEmpty() || jobTitle.isBlank())
                return null;
            return builder.like(root.get("jobTitle"), "%"+ jobTitle + "%");
        };
    }

    public Specification<Employee> searchByContractStartDateBetween(LocalDate contractStartDate, LocalDate contractEndDate){
        return (root, query, cb)->{
            if(contractStartDate == null){
                return null;
            }
            return cb.between(root.get("contractStartDate"), contractStartDate, contractEndDate);
        };
    }
}

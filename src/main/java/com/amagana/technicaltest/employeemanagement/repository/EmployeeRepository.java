package com.amagana.technicaltest.employeemanagement.repository;

import com.amagana.technicaltest.employeemanagement.entity.Department;
import com.amagana.technicaltest.employeemanagement.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {
    List<Employee> findAllByManager(Employee manager);
    List<Employee> findAllByDepartment(Department department);

}

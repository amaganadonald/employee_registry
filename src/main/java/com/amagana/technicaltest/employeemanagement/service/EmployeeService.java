package com.amagana.technicaltest.employeemanagement.service;

import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoResponse;
import com.amagana.technicaltest.employeemanagement.dtos.PageResultDto;
import com.amagana.technicaltest.employeemanagement.dtos.SearchDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDtoResponse> getAllEmployees();
    PageResultDto<EmployeeDtoResponse> getEmployeePage(int page, int size);
    EmployeeDtoResponse getEmployeeById(Long id);
    EmployeeDtoResponse createEmployee(EmployeeDtoRequest employeeDtoRequest);
    EmployeeDtoResponse updateEmployee(Long id, EmployeeDtoRequest employeeDtoRequest);
    EmployeeDtoResponse assignDepartment(Long departmentId, Long employeeId);
    void deleteEmployee(Long id);
    List<EmployeeDtoResponse> filterEmployees(SearchDto searchDto);
    List<EmployeeDtoResponse> getEmployeesByDepartment(Long departmentId);
    List<EmployeeDtoResponse> getEmployeesByManager(Long managerId);
    List<EmployeeDtoResponse> createListEmployees(List<EmployeeDtoRequest> employeeDtoRequests);
}

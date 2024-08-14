package com.amagana.technicaltest.employeemanagement.service;

import com.amagana.technicaltest.employeemanagement.dtos.DepartmentDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.DepartmentDtoResponse;
import com.amagana.technicaltest.employeemanagement.entity.Department;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DepartmentService {

    List<DepartmentDtoResponse> getAllDepartment();
    Page<DepartmentDtoResponse> getAllDepartmentByPage(int page, int size);
    DepartmentDtoResponse getDepartmentById(Long id);
    Department findDepartmentById(Long id);
    DepartmentDtoResponse createDepartment(DepartmentDtoRequest departmentDtoRequest);
    DepartmentDtoResponse updateDepartment(Long id, DepartmentDtoRequest departmentDtoRequest);
    void deleteDepartment(Long id);
    List<DepartmentDtoResponse> createListOfDepartment(List<DepartmentDtoRequest> departmentDtoRequest);
}

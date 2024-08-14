package com.amagana.technicaltest.employeemanagement.utilis;

import com.amagana.technicaltest.employeemanagement.dtos.DepartmentDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.DepartmentDtoResponse;
import com.amagana.technicaltest.employeemanagement.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper instance = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDtoResponse departmentToDepartmentDtoResponse(Department department);

    Department departmentDtoRequestToDepartment(DepartmentDtoRequest departmentDtoRequest);
}

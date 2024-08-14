package com.amagana.technicaltest.employeemanagement.utilis;

import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoResponse;
import com.amagana.technicaltest.employeemanagement.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
    EmployeeMapper instance = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDtoResponse employeeToEmployeeDtoResponse(Employee employee);

    Employee employeeDtoRequestToEmployee(EmployeeDtoRequest employeeDtoRequest);

}

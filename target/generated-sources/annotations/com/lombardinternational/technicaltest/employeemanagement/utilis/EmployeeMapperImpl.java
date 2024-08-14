package com.lombardinternational.technicaltest.employeemanagement.utilis;

import com.lombardinternational.technicaltest.employeemanagement.dtos.EmployeeDtoRequest;
import com.lombardinternational.technicaltest.employeemanagement.dtos.EmployeeDtoResponse;
import com.lombardinternational.technicaltest.employeemanagement.entity.Employee;
import com.lombardinternational.technicaltest.employeemanagement.enums.Gender;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-26T09:55:54+0200",
    comments = "version: 1.6.0.RC1, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDtoResponse employeeToEmployeeDtoResponse(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDtoResponse.EmployeeDtoResponseBuilder employeeDtoResponse = EmployeeDtoResponse.builder();

        employeeDtoResponse.id( employee.getId() );
        employeeDtoResponse.firstName( employee.getFirstName() );
        employeeDtoResponse.lastName( employee.getLastName() );
        employeeDtoResponse.jobTitle( employee.getJobTitle() );
        employeeDtoResponse.contractStartDate( employee.getContractStartDate() );
        employeeDtoResponse.contractEndDate( employee.getContractEndDate() );
        employeeDtoResponse.gender( employee.getGender() );
        employeeDtoResponse.department( employee.getDepartment() );
        employeeDtoResponse.grade( employee.getGrade() );

        return employeeDtoResponse.build();
    }

    @Override
    public Employee employeeDtoRequestToEmployee(EmployeeDtoRequest employeeDtoRequest) {
        if ( employeeDtoRequest == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.id( employeeDtoRequest.id() );
        employee.firstName( employeeDtoRequest.firstName() );
        employee.lastName( employeeDtoRequest.lastName() );
        employee.jobTitle( employeeDtoRequest.jobTitle() );
        employee.contractStartDate( employeeDtoRequest.contractStartDate() );
        employee.contractEndDate( employeeDtoRequest.contractEndDate() );
        if ( employeeDtoRequest.gender() != null ) {
            employee.gender( Enum.valueOf( Gender.class, employeeDtoRequest.gender() ) );
        }
        employee.createdUserName( employeeDtoRequest.createdUserName() );

        return employee.build();
    }
}

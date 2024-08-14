package com.amagana.technicaltest.employeemanagement.service.impl;

import com.amagana.technicaltest.employeemanagement.config.SpecificationSearch;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoResponse;
import com.amagana.technicaltest.employeemanagement.dtos.PageResultDto;
import com.amagana.technicaltest.employeemanagement.dtos.SearchDto;
import com.amagana.technicaltest.employeemanagement.entity.Department;
import com.amagana.technicaltest.employeemanagement.entity.Employee;
import com.amagana.technicaltest.employeemanagement.entity.Grade;
import com.amagana.technicaltest.employeemanagement.exceptions.EntityNotFoundException;
import com.amagana.technicaltest.employeemanagement.repository.EmployeeRepository;
import com.amagana.technicaltest.employeemanagement.service.DepartmentService;
import com.amagana.technicaltest.employeemanagement.service.EmployeeService;
import com.amagana.technicaltest.employeemanagement.service.GradeService;
import com.amagana.technicaltest.employeemanagement.utilis.EmployeeMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentService departmentService;
    private final GradeService gradeService;
    private static final EmployeeMapper employeeMapper = EmployeeMapper.instance;

    @Override
    public List<EmployeeDtoResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::employeeToEmployeeDtoResponse)
                .toList();
    }

    @Override
    public PageResultDto<EmployeeDtoResponse> getEmployeePage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeDtoResponse> employeeResponse = employeeRepository.findAll(pageable).map(employeeMapper::employeeToEmployeeDtoResponse);
        return PageResultDto.<EmployeeDtoResponse>builder()
                .content(employeeResponse.getContent())
                .totalElements(employeeResponse.getTotalElements())
                .totalPages(employeeResponse.getTotalPages())
                .first(employeeResponse.isFirst())
                .last(employeeResponse.isLast())
                .number(employeeResponse.getNumber())
                .size(employeeResponse.getSize())
                .build();
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Employee not Found with id " + id));
    }

    @Override
    public EmployeeDtoResponse getEmployeeById(Long id) {
        return employeeMapper.employeeToEmployeeDtoResponse(
                findEmployeeById(id)
        );
    }

    @Override
    public EmployeeDtoResponse createEmployee(EmployeeDtoRequest employeeDtoRequest) {
        Employee employee = employeeMapper.employeeDtoRequestToEmployee(employeeDtoRequest);
        if (employeeDtoRequest.departmentId() != 0) {
            Department department = departmentService.findDepartmentById(employeeDtoRequest.departmentId());
            employee.setDepartment(department);
        }
        if (employeeDtoRequest.gradeId() != 0) {
            Grade grade = gradeService.findGradeById(employeeDtoRequest.gradeId());
            employee.setGrade(grade);
        }
        if(employeeDtoRequest.managerId() != 0) {
            Employee manager = findEmployeeById(employeeDtoRequest.managerId());
            employee.setManager(manager);
        }
        return employeeMapper.employeeToEmployeeDtoResponse(
                employeeRepository.save(employee)
        );
    }

    @Override
    public EmployeeDtoResponse updateEmployee(Long id, EmployeeDtoRequest employeeDtoRequest) {
        Employee employee = findEmployeeById(id);
        if (employeeDtoRequest.departmentId() != 0) {
            Department department = departmentService.findDepartmentById(employeeDtoRequest.departmentId());
            employee.setDepartment(department);
        }
        if (employeeDtoRequest.gradeId() != 0) {
            Grade grade = gradeService.findGradeById(employeeDtoRequest.gradeId());
            employee.setGrade(grade);
        }
        if(employeeDtoRequest.managerId() != 0) {
            Employee manager = findEmployeeById(employeeDtoRequest.managerId());
            employee.setManager(manager);
        }
        employee.setFirstName(employeeDtoRequest.firstName());
        employee.setLastName(employeeDtoRequest.lastName());
        employee.setContractEndDate(employeeDtoRequest.contractEndDate());
        employee.setContractStartDate(employeeDtoRequest.contractStartDate());
        return employeeMapper.employeeToEmployeeDtoResponse(
                employeeRepository.save(employee)
        );
    }

    @Override
    public EmployeeDtoResponse assignDepartment(Long departmentId, Long employeeId) {
        Employee employee = findEmployeeById(employeeId);
        Department department = departmentService.findDepartmentById(departmentId);
        employee.setDepartment(department);
        return employeeMapper.employeeToEmployeeDtoResponse(
                employeeRepository.save(employee)
        );
    }

    @Override
    public void deleteEmployee(Long id) {
       employeeRepository.delete(findEmployeeById(id));
    }

    @Override
    public List<EmployeeDtoResponse> filterEmployees(SearchDto searchDto) {
        Specification<Employee> spec = Specification.where(SpecificationSearch.searchByFirstName(searchDto.firstName()))
                .or(SpecificationSearch.searchByLastName(searchDto.lastName()))
                .or(SpecificationSearch.searchByJobTitle(searchDto.jobTitle()))
                .or(SpecificationSearch.searchByContractStartDateBetween(searchDto.startDate(),searchDto.endDate()));
        return employeeRepository.findAll(spec)
                .stream()
                .map(employeeMapper::employeeToEmployeeDtoResponse)
                .toList();
    }

    @Override
    public List<EmployeeDtoResponse> getEmployeesByDepartment(Long departmentId) {
        Department department = departmentService.findDepartmentById(departmentId);
        return employeeRepository.findAllByDepartment(department)
                .stream()
                .map(employeeMapper::employeeToEmployeeDtoResponse)
                .toList() ;
    }

    @Override
    public List<EmployeeDtoResponse> getEmployeesByManager(Long managerId) {
        Employee manager = findEmployeeById(managerId);
        return employeeRepository.findAllByManager(manager)
                .stream()
                .map(employeeMapper::employeeToEmployeeDtoResponse)
                .toList() ;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public List<EmployeeDtoResponse> createListEmployees(List<EmployeeDtoRequest> employeeDtoRequests) {
        List<Employee> employees = employeeDtoRequests.stream().map(employeeMapper::employeeDtoRequestToEmployee)
                .toList();
        return employeeRepository.saveAllAndFlush(employees)
                .stream()
                .map(employeeMapper::employeeToEmployeeDtoResponse)
                .toList();
    }
}

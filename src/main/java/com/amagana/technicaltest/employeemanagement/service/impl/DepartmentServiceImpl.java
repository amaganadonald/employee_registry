package com.amagana.technicaltest.employeemanagement.service.impl;

import com.amagana.technicaltest.employeemanagement.dtos.DepartmentDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.DepartmentDtoResponse;
import com.amagana.technicaltest.employeemanagement.entity.Department;
import com.amagana.technicaltest.employeemanagement.exceptions.EntityNotFoundException;
import com.amagana.technicaltest.employeemanagement.repository.DepartmentRepository;
import com.amagana.technicaltest.employeemanagement.service.DepartmentService;
import com.amagana.technicaltest.employeemanagement.utilis.DepartmentMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    public static final DepartmentMapper departmentMapper = DepartmentMapper.instance;
    @Override
    public List<DepartmentDtoResponse> getAllDepartment() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::departmentToDepartmentDtoResponse)
                .toList();
    }

    @Override
    public Page<DepartmentDtoResponse> getAllDepartmentByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return departmentRepository.findAll(pageable).map(departmentMapper::departmentToDepartmentDtoResponse);
    }

    @Override
    public DepartmentDtoResponse getDepartmentById(Long id) {
        return departmentMapper.departmentToDepartmentDtoResponse(findDepartmentById(id));
    }

    @Override
    public Department findDepartmentById(Long id) {
       return departmentRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Department not found with id " + id));
    }

    @Override
    public DepartmentDtoResponse createDepartment(DepartmentDtoRequest departmentDtoRequest) {
        return departmentMapper.departmentToDepartmentDtoResponse(
                departmentRepository.save(departmentMapper.departmentDtoRequestToDepartment(departmentDtoRequest))
        );
    }

    @Override
    public DepartmentDtoResponse updateDepartment(Long id, DepartmentDtoRequest departmentDtoRequest) {
        Department department = findDepartmentById(id);
        department.setName(departmentDtoRequest.name());
        return departmentMapper.departmentToDepartmentDtoResponse(
                departmentRepository.save(department)
        );
    }

    @Override
    public void deleteDepartment(Long id) {
       departmentRepository.delete(findDepartmentById(id));
    }

    @Override
    public List<DepartmentDtoResponse> createListOfDepartment(List<DepartmentDtoRequest> departmentDtoRequest) {
        List<Department> departments = departmentDtoRequest.stream().map(departmentMapper::departmentDtoRequestToDepartment).toList();
        return departmentRepository.saveAll(departments).stream()
                .map(departmentMapper::departmentToDepartmentDtoResponse)
                .toList();
    }
}

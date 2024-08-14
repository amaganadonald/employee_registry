package com.amagana.technicaltest.employeemanagement.controller;

import com.amagana.technicaltest.employeemanagement.dtos.DepartmentDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.DepartmentDtoResponse;
import com.amagana.technicaltest.employeemanagement.enums.StatusResponse;
import com.amagana.technicaltest.employeemanagement.models.APIResponse;
import com.amagana.technicaltest.employeemanagement.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/department")
public class DepartmentController {
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<APIResponse<List<DepartmentDtoResponse>>> getDepartments() {
        return ResponseEntity.ok(APIResponse.apiResponseList(
                StatusResponse.SUCCESS, departmentService.getAllDepartment()
        ));
    }

    @GetMapping("/page")
    public ResponseEntity<APIResponse<Page<DepartmentDtoResponse>>> getDepartmentsPage(
            @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(APIResponse.apiResponsePage(
                StatusResponse.SUCCESS, departmentService.getAllDepartmentByPage(page, size)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<DepartmentDtoResponse>> getDepartmentById(@PathVariable Long id) {
        return ResponseEntity.ok(APIResponse.apiResponseSingle(
                StatusResponse.SUCCESS, departmentService.getDepartmentById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<APIResponse<DepartmentDtoResponse>> createDepartment(@RequestBody DepartmentDtoRequest departmentDtoRequest) {
        return ResponseEntity.ok(APIResponse.apiResponseSingle(
                StatusResponse.SUCCESS, departmentService.createDepartment(departmentDtoRequest)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<DepartmentDtoResponse>> updateDepartment(@PathVariable Long id, @RequestBody DepartmentDtoRequest departmentDtoRequest) {
        return ResponseEntity.ok(APIResponse.apiResponseSingle(
                StatusResponse.SUCCESS, departmentService.updateDepartment(id, departmentDtoRequest)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(APIResponse.apiResponseSingle(
                StatusResponse.SUCCESS, "Department deleted successfully"
        ));
    }
}

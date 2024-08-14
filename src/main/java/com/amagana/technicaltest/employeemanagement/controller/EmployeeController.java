package com.amagana.technicaltest.employeemanagement.controller;

import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoResponse;
import com.amagana.technicaltest.employeemanagement.dtos.PageResultDto;
import com.amagana.technicaltest.employeemanagement.dtos.SearchDto;
import com.amagana.technicaltest.employeemanagement.enums.StatusResponse;
import com.amagana.technicaltest.employeemanagement.models.APIResponse;
import com.amagana.technicaltest.employeemanagement.service.EmployeeService;
import com.amagana.technicaltest.employeemanagement.service.IEmployeeByFirstname;
import com.amagana.technicaltest.employeemanagement.service.IEmployeeByLastName;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<APIResponse<List<EmployeeDtoResponse>>> getEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseList(
                StatusResponse.SUCCESS, employeeService.getAllEmployees()
        ));
    }

    @GetMapping("/page")
    public ResponseEntity<APIResponse<PageResultDto<EmployeeDtoResponse>>> getEmployeePage(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                                           @RequestParam(name = "size", defaultValue = "10") int size) {
        return  ResponseEntity.ok(APIResponse.apiResponsePagination(
                StatusResponse.SUCCESS, employeeService.getEmployeePage(page, size)
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<EmployeeDtoResponse>> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingle(
                StatusResponse.SUCCESS, employeeService.getEmployeeById(id)
        ));
    }

    @GetMapping("/employeeByDepartment/{id}")
    public ResponseEntity<APIResponse<List<EmployeeDtoResponse>>> getEmployeeByDepartment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseList(
                StatusResponse.SUCCESS, employeeService.getEmployeesByDepartment(id)
        ));
    }

    @GetMapping("/employeeUnderManager/{id}")
    public ResponseEntity<APIResponse<List<EmployeeDtoResponse>>> getEmployeeUnderManager(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseList(
                StatusResponse.SUCCESS, employeeService.getEmployeesByManager(id)
        ));
    }

    @PostMapping
    public ResponseEntity<APIResponse<EmployeeDtoResponse>> createEmployee(@RequestBody @Validated(IEmployeeByFirstname.class)
                                                                               EmployeeDtoRequest employeeDtoRequest) {
        Cookie cookie = new Cookie("employee_cookie", "preference_user");
        cookie.setMaxAge(60 * 60 * 24 * 30);  // 30 days
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE, String.format("%s=%s; Path=%s; Max-Age=%d; HttpOnly=%b; Secure=%b",
                cookie.getName(), cookie.getValue(), cookie.getPath(), cookie.getMaxAge(), cookie.isHttpOnly(), cookie.getSecure()));
        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(APIResponse.apiResponseSingle(
                StatusResponse.SUCCESS, employeeService.createEmployee(employeeDtoRequest)
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<EmployeeDtoResponse>> updateEmployee(@PathVariable Long id,
                                                                           @Validated(IEmployeeByLastName.class) @RequestBody @Valid EmployeeDtoRequest employeeDtoRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingle(
                StatusResponse.SUCCESS, employeeService.updateEmployee(id, employeeDtoRequest)
        ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseSingle(
                StatusResponse.SUCCESS, "Employee deleted successfully"
        ));
    }

    @GetMapping("/assign-department/{departmentId}/{employeeId}")
    public ResponseEntity<APIResponse<EmployeeDtoResponse>> assignDepartment(@PathVariable Long departmentId, @PathVariable Long employeeId) {
        return  ResponseEntity.ok(APIResponse.apiResponseSingle(
                StatusResponse.SUCCESS, employeeService.assignDepartment(departmentId, employeeId)
        ));
    }


    @PostMapping("/search")
    public ResponseEntity<APIResponse<List<EmployeeDtoResponse>>> searchEmployees(@RequestBody @Valid SearchDto searchDto) {
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse.apiResponseList(
                StatusResponse.SUCCESS, employeeService.filterEmployees(searchDto)
        ));
    }
}

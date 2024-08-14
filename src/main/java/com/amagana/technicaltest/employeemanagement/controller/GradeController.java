package com.amagana.technicaltest.employeemanagement.controller;

import com.amagana.technicaltest.employeemanagement.dtos.GradeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.GradeDtoResponse;
import com.amagana.technicaltest.employeemanagement.dtos.PageResultDto;
import com.amagana.technicaltest.employeemanagement.enums.StatusResponse;
import com.amagana.technicaltest.employeemanagement.models.APIResponse;
import com.amagana.technicaltest.employeemanagement.service.GradeProjection;
import com.amagana.technicaltest.employeemanagement.service.GradeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/grade")
public class GradeController {
    private final GradeService gradeService;

    @GetMapping
    public ResponseEntity<APIResponse<List<GradeDtoResponse>>> getAllGrade() {
        return ResponseEntity.ok(APIResponse.apiResponseList(
                StatusResponse.SUCCESS, gradeService.getAllGrade()
        ));
    }

    @GetMapping("/page")
    public ResponseEntity<APIResponse<PageResultDto<GradeProjection>>> getGradePage(
            @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(APIResponse.apiResponsePagination(StatusResponse.SUCCESS, gradeService.getGradePage(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<GradeDtoResponse>> getGradeById(@PathVariable Long id) {
        return ResponseEntity.ok(APIResponse.apiResponseSingle(StatusResponse.SUCCESS, gradeService.getGradeById(id)));
    }

    @PostMapping
    public ResponseEntity<APIResponse<GradeDtoResponse>> createGrade(@RequestBody GradeDtoRequest gradeDtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(APIResponse.apiResponseSingle(StatusResponse.SUCCESS,
                gradeService.createGrade(gradeDtoRequest)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<GradeDtoResponse>> updateGrade(@PathVariable Long id, @RequestBody GradeDtoRequest gradeDtoRequest) {
        return ResponseEntity.ok(APIResponse.apiResponseSingle(StatusResponse.SUCCESS,
                gradeService.updateGrade(id, gradeDtoRequest)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> deleteGrade(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.ok(APIResponse.apiResponseSingle(StatusResponse.SUCCESS, "Grade deleted successfully"));
    }
}

package com.amagana.technicaltest.employeemanagement.service;

import com.amagana.technicaltest.employeemanagement.dtos.GradeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.GradeDtoResponse;
import com.amagana.technicaltest.employeemanagement.dtos.PageResultDto;
import com.amagana.technicaltest.employeemanagement.entity.Grade;

import java.util.List;

public interface GradeService {

    List<GradeDtoResponse> getAllGrade();
    PageResultDto<GradeProjection> getGradePage(int page, int size);
    GradeDtoResponse getGradeById(Long id);
    Grade findGradeById(Long id);
    GradeDtoResponse createGrade(GradeDtoRequest gradeDtoRequest);
    GradeDtoResponse updateGrade(Long id, GradeDtoRequest gradeDtoRequest);
    void deleteGrade(Long id);
    List<GradeDtoResponse> createListGrade(List<GradeDtoRequest> gradeDtoRequest);
}

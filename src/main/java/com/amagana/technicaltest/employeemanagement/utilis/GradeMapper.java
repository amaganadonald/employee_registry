package com.amagana.technicaltest.employeemanagement.utilis;

import com.amagana.technicaltest.employeemanagement.dtos.GradeDtoRequest;
import com.amagana.technicaltest.employeemanagement.dtos.GradeDtoResponse;
import com.amagana.technicaltest.employeemanagement.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GradeMapper {
    GradeMapper instance = Mappers.getMapper(GradeMapper.class);

    GradeDtoResponse gradeToGradeDtoResponse(Grade grade);

    Grade gradeDtoRequestToGrade(GradeDtoRequest gradeDtoRequest);
}

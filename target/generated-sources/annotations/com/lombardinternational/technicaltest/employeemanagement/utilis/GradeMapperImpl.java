package com.lombardinternational.technicaltest.employeemanagement.utilis;

import com.lombardinternational.technicaltest.employeemanagement.dtos.GradeDtoRequest;
import com.lombardinternational.technicaltest.employeemanagement.dtos.GradeDtoResponse;
import com.lombardinternational.technicaltest.employeemanagement.entity.Grade;
import java.util.Date;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-26T09:55:54+0200",
    comments = "version: 1.6.0.RC1, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
public class GradeMapperImpl implements GradeMapper {

    @Override
    public GradeDtoResponse gradeToGradeDtoResponse(Grade grade) {
        if ( grade == null ) {
            return null;
        }

        Long id = null;
        String gradeName = null;
        Date fromDate = null;
        Date toDate = null;

        id = grade.getId();
        gradeName = grade.getGradeName();
        fromDate = grade.getFromDate();
        toDate = grade.getToDate();

        GradeDtoResponse gradeDtoResponse = new GradeDtoResponse( id, gradeName, fromDate, toDate );

        return gradeDtoResponse;
    }

    @Override
    public Grade gradeDtoRequestToGrade(GradeDtoRequest gradeDtoRequest) {
        if ( gradeDtoRequest == null ) {
            return null;
        }

        Grade.GradeBuilder grade = Grade.builder();

        grade.id( gradeDtoRequest.id() );
        grade.gradeName( gradeDtoRequest.gradeName() );
        grade.fromDate( gradeDtoRequest.fromDate() );
        grade.toDate( gradeDtoRequest.toDate() );

        return grade.build();
    }
}

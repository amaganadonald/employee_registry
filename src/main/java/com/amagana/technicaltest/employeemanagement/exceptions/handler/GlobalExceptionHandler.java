package com.amagana.technicaltest.employeemanagement.exceptions.handler;

import com.amagana.technicaltest.employeemanagement.dtos.ErrorsDto;
import com.amagana.technicaltest.employeemanagement.enums.StatusResponse;
import com.amagana.technicaltest.employeemanagement.exceptions.EmployeeBussnessException;
import com.amagana.technicaltest.employeemanagement.exceptions.EntityNotFoundException;
import com.amagana.technicaltest.employeemanagement.models.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<List<ErrorsDto>>> validationErrors(MethodArgumentNotValidException exception){
        List<ErrorsDto> errors = exception.getFieldErrors().stream()
                .map(error-> ErrorsDto.builder().field(error.getField()).message(error.getDefaultMessage()).build())
                .toList();
        return ResponseEntity.badRequest().body(APIResponse.apiResponseErrors(StatusResponse.FAILED, errors));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<APIResponse<String>> entityNotFound(EntityNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.apiResponseMessage(StatusResponse.FAILED,
                exception.getMessage()));
    }

    @ExceptionHandler(EmployeeBussnessException.class)
    public ResponseEntity<APIResponse<String>> globalExceptionHandler(EmployeeBussnessException exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.apiResponseMessage(StatusResponse.FAILED,
                exception.getMessage()));
    }
}

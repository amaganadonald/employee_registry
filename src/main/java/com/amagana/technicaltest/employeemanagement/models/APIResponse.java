package com.amagana.technicaltest.employeemanagement.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.amagana.technicaltest.employeemanagement.dtos.EmployeeDtoResponse;
import com.amagana.technicaltest.employeemanagement.dtos.PageResultDto;
import com.amagana.technicaltest.employeemanagement.dtos.ErrorsDto;
import com.amagana.technicaltest.employeemanagement.enums.StatusResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
    private StatusResponse statusResponse;
    private T results;
    private List<ErrorsDto> errors;
    private String message;

    public static APIResponse<String> apiResponseMessage(StatusResponse statusResponse, String message){
        return APIResponse.<String>builder()
               .statusResponse(statusResponse)
               .message(message)
               .build();
    }
    public static <T> APIResponse<T> apiResponseSingle(StatusResponse statusResponse, T results){
        return APIResponse.<T>builder()
               .statusResponse(statusResponse)
               .results(results)
               .build();
    }
    public static <T> APIResponse<List<T>> apiResponseList(StatusResponse statusResponse, List<T> results) {
        return APIResponse.<List<T>>builder()
               .statusResponse(statusResponse)
               .results(results)
               .build();
    }
    public static <T> APIResponse<Page<T>> apiResponsePage(StatusResponse statusResponse, Page<T> results) {
        return APIResponse.<Page<T>>builder()
                .statusResponse(statusResponse)
                .results(results)
                .build();
    }
    public static  APIResponse<List<ErrorsDto>> apiResponseErrors(StatusResponse statusResponse, List<ErrorsDto> errors){
        return APIResponse.<List<ErrorsDto>>builder()
               .statusResponse(statusResponse)
               .errors(errors)
               .build();
    }
    public static <T> APIResponse<PageResultDto<T>> apiResponsePagination(StatusResponse statusResponse, PageResultDto<T> results){
        return APIResponse.<PageResultDto<T>>builder()
               .statusResponse(statusResponse)
               .results(results)
               .build();
    }
}

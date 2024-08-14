package com.amagana.technicaltest.employeemanagement.utilis;

import com.amagana.technicaltest.employeemanagement.dtos.PageResultDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageDtoMapper<T> {

    public PageResultDto<T> createPageResult(Page<T> result){
        return PageResultDto.<T>builder()
                .totalPages(result.getTotalPages())
                .content(result.getContent())
                .first(result.isFirst())
                .last(result.isLast())
                .number(result.getNumber())
                .size(result.getSize())
                .totalElements(result.getTotalElements())
                .build();
    }
}

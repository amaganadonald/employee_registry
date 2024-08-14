package com.amagana.technicaltest.employeemanagement.repository;

import com.amagana.technicaltest.employeemanagement.entity.Grade;
import com.amagana.technicaltest.employeemanagement.service.GradeProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long>, JpaSpecificationExecutor<Grade> {

    Page<GradeProjection> findAllBy(Pageable pageable);

    @Transactional
    List<Grade> findGradeByGradeName(@Param("gradeName") String gradeName);

    @Transactional
    @Modifying
    int updateGradeBeginWiths(@Param("gradeName") String gradeName, @Param("gradeMotif") String gradeMotif);
}

package com.amagana.technicaltest.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@JsonIgnoreProperties(value = {"employees"})

@NamedQuery(name="Grade.findGradeByGradeName", query = "select g from Grade g where g.gradeName=:gradeName")
@NamedQuery(name="Grade.updateGradeBeginWiths", query="update Grade g set g.gradeName=:gradeName where gradeName like '%:gradeMotif'")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grade_seq")
    @SequenceGenerator(name = "grade_seq")
    private Long id;
    @Column(name = "grade_name")
    private String gradeName;
    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;
    @OneToMany(mappedBy = "grade")
    @ToString.Exclude
    private Set<Employee> employees = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grade grade)) return false;
        return Objects.equals(id, grade.id) && Objects.equals(gradeName, grade.gradeName) && Objects.equals(fromDate, grade.fromDate) && Objects.equals(toDate, grade.toDate) && Objects.equals(employees, grade.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gradeName, fromDate, toDate, employees);
    }
}

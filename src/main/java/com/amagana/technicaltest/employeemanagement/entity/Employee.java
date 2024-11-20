package com.amagana.technicaltest.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.amagana.technicaltest.employeemanagement.enums.Gender;
import com.amagana.technicaltest.employeemanagement.validation.ValidateStartDateBeforeEndDate;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@ValidateStartDateBeforeEndDate
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
    @SequenceGenerator(name = "employee_seq", initialValue = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "contract_start_date")
    private LocalDate contractStartDate;
    @Column(name = "contract_end_date")
    private LocalDate contractEndDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JsonBackReference
    private Department department;

    @ManyToOne()
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties(value = "listSubaltern")
    private Employee manager;

   @JsonIgnoreProperties(value = "manager")
    @OneToMany(mappedBy = "manager")
    @ToString.Exclude
    private List<Employee> listSubaltern;

    @ManyToOne()
    @JsonBackReference
    private Grade grade;

    @Column(name = "created_user_name")
    private String createdUserName;
    @Version
    private int version;

    @ElementCollection
    @CollectionTable(name = "employee_phone_number", joinColumns = @JoinColumn(name = "employee_id"))
    @Column(name = "phone_number")
    private Set<String> phones = new HashSet<>();

    @Embedded
    private Address address;
}

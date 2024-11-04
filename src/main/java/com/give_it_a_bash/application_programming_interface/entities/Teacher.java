/*
 * Copyright (c) 2024 Give It A Bash
 *
 * This file is part of Give It A Bash proprietary software.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 * Proprietary and confidential.
 *
 * Created and maintained by Give It A Bash.
 */

package com.give_it_a_bash.application_programming_interface.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a teacher who is a mutant with unique powers and characteristics.
 * This class includes information about the teacher's contact details, qualifications,
 * experience, and the subjects they teach.
 */
@Entity
@Getter
@Setter
public class Teacher extends Mutant {

    /**
     * The email address of the teacher.
     */
    @NotBlank(message = "Email must not be blank")
    private String email;

    /**
     * The phone number of the teacher.
     */
    @NotBlank(message = "Phone number must not be blank")
    private String phoneNumber;

    /**
     * The address of the teacher.
     */
    private String address;

    /**
     * The qualifications of the teacher.
     */
    private String qualifications;

    /**
     * The number of years of experience the teacher has.
     */
    private int yearsOfExperience;

    /**
     * The department in which the teacher works.
     */
    private String department;

    /**
     * The list of lessons that this teacher is responsible for.
     */
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;

    /**
     * The subjects that this teacher is qualified to teach.
     */
    @ManyToMany
    @JoinTable(
            name = "teacher_subjects",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private List<Subject> subjects;

    /**
     * Custom constructor for the Teacher class.
     */
    @Builder
    public Teacher(SchoolData schoolData,
                   String firstName,
                   String lastName,
                   String alias,
                   Power power,
                   List<String> missionHistory,
                   boolean isActive,
                   String email,
                   String phoneNumber,
                   String address,
                   String qualifications,
                   int yearsOfExperience,
                   String department,
                   List<Lesson> lessons,
                   List<Subject> subjects) {
        super(schoolData, firstName, lastName, alias, power, missionHistory, isActive);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.qualifications = qualifications;
        this.yearsOfExperience = yearsOfExperience;
        this.department = department;
        this.lessons = lessons != null ? lessons : new ArrayList<>();
        this.subjects = subjects != null ? subjects : new ArrayList<>();
    }
}

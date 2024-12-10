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

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student who is a mutant with unique powers and characteristics.
 * This class includes information about the student, their guardian, and their
 * academic achievements.
 */
@Entity
@Getter
@Setter
public class Student extends Mutant {

    /**
     * The first name of the guardian.
     */
    @NotBlank(message = "Guardian first name must not be blank")
    private String guardianFirstName;

    /**
     * The last name of the guardian.
     */
    @NotBlank(message = "Guardian last name must not be blank")
    private String guardianLastName;

    /**
     * The contact number of the guardian.
     */
    @NotBlank(message = "Guardian contact number must not be blank")
    private String guardianContactNumber;

    /**
     * The email address of the guardian.
     */
    @Email(message = "Guardian email must be valid")
    private String guardianEmail;

    /**
     * The contact number of the student.
     */
    @NotBlank(message = "Student contact number must not be blank")
    private String contactNumber;

    /**
     * The email address of the student.
     */
    @Email(message = "Student email must be valid")
    private String email;

    /**
     * The lessons that this student is enrolled in.
     */
    @ManyToMany
    @JoinTable(
            name = "student_lessons",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    @JsonIgnore
    private List<Lesson> lessons;

    /**
     * The achievements earned by this student.
     */
    @OneToMany
    @JsonIgnore
    private List<Achievement> achievements;

    /**
     * The current status of the student.
     */
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status must not be null")
    private Status status;

    /**
     * Constructor for the Student class.
     */
    @Builder
    public Student(SchoolData schoolData,
                   String firstName,
                   String lastName,
                   String alias,
                   Power power,
                   List<String> missionHistory,
                   boolean isActive,
                   String guardianFirstName,
                   String guardianLastName,
                   String guardianContactNumber,
                   String guardianEmail,
                   String contactNumber,
                   String email,
                   List<Lesson> lessons,
                   List<Achievement> achievements,
                   Status status) {
        super(schoolData, firstName, lastName, alias, power, missionHistory, isActive);
        this.guardianFirstName = guardianFirstName;
        this.guardianLastName = guardianLastName;
        this.guardianContactNumber = guardianContactNumber;
        this.guardianEmail = guardianEmail;
        this.contactNumber = contactNumber;
        this.email = email;
        this.lessons = lessons != null ? lessons : new ArrayList<>();
        this.achievements = achievements != null ? achievements : new ArrayList<>();
        this.status = status;
    }

    // No-args constructor (required by Hibernate)
    public Student() {
        super(null, null, null, null, null, null,
                false);
    }
}

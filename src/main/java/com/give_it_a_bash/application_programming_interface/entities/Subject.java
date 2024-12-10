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
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an academic subject offered by the school.
 * Each subject is associated with a specific school,
 * can have multiple students enrolled, and is taught by a teacher.
 */
@Entity
@Getter
@Setter
public class Subject {

    /**
     * The unique identifier for the subject.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The school that offers this subject.
     */
    @ManyToOne
    @JoinColumn(name = "school_data_id", nullable = false)
    private SchoolData schoolData;

    /**
     * The name of the subject. Cannot be blank.
     */
    @NotBlank(message = "Subject name must not be blank")
    private String name;

    /**
     * The list of students enrolled in this subject.
     */
    @OneToMany
    @JsonIgnore
    private List<Lesson> lessons;

    /**
     * The teacher responsible for this subject.
     */
    @ManyToMany(mappedBy = "subjects")
    @JsonIgnore
    private List<Teacher> teacher;

    // No-argument constructor
    public Subject() {
    }

    @Builder
    public Subject(String name, List<Lesson> lessons, SchoolData schoolData, List<Teacher> teachers) {
        this.name = name;
        this.lessons = lessons != null ? lessons : new ArrayList<>();
        this.schoolData = schoolData != null ? schoolData : new SchoolData();
        this.teacher = teacher != null ? teachers : new ArrayList<>();
    }
}

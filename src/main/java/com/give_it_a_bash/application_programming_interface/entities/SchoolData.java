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
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents the data related to a school, including its name, location,
 * affiliation, and associated entities such as students, teachers, subjects,
 * and facilities.
 */
@Getter
@Setter
@Entity
@Builder
public class SchoolData {

    /**
     * Unique identifier for the school data.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the school.
     */
    private String schoolName;

    /**
     * The physical location of the school.
     */
    private String location;

    /**
     * The motto of the school.
     */
    private String motto;

    /**
     * The year the school was established.
     */
    private int yearEstablished;

    /**
     * The affiliation of the school (e.g., religious, educational organization).
     */
    private String affiliation;

    /**
     * Contact information for the school.
     */
    private String contactInfo;

    /**
     * List of students associated with the school.
     */
    @OneToMany(mappedBy = "schoolData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    /**
     * List of teachers associated with the school.
     */
    @OneToMany(mappedBy = "schoolData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Teacher> teachers;

    /**
     * List of subjects offered at the school.
     */
    @OneToMany(mappedBy = "schoolData", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects;

    /**
     * List of facilities available at the school.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "school_data_id")
    private List<Facility> facilities;

    /**
     * Indicates whether the school is currently active.
     */
    @Column(name = "is_school_active", nullable = false)
    private boolean isActive;
}

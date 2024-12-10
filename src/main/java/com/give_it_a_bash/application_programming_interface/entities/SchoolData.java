package com.give_it_a_bash.application_programming_interface.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the data related to a school, including its name, location,
 * affiliation, and associated entities such as students, teachers, subjects,
 * and facilities.
 */
@Getter
@Setter
@Entity
@ToString
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
    @OneToMany
    @JsonIgnore
    private List<Student> students = new ArrayList<>();

    /**
     * List of teachers associated with the school.
     */
    @OneToMany
    @JsonIgnore
    private List<Teacher> teachers = new ArrayList<>();

    /**
     * List of subjects offered at the school.
     */
    @OneToMany
    @JsonIgnore
    private List<Subject> subjects = new ArrayList<>();

    /**
     * List of facilities available at the school.
     */
    @OneToMany
    @JsonIgnore
    private List<Facility> facilities = new ArrayList<>();

    /**
     * Indicates whether the school is currently active.
     */
    @Column(name = "is_school_active", nullable = false)
    private boolean isActive;

    /**
     * No-argument constructor (required by Hibernate).
     */
    public SchoolData() {
    }

    /**
     * Custom constructor for the SchoolData class.
     */
    @Builder
    public SchoolData(String schoolName, String location, String motto, int yearEstablished, String affiliation,
                      String contactInfo, boolean isActive, List<Student> students, List<Teacher> teachers,
                      List<Subject> subjects, List<Facility> facilities) {
        this.schoolName = schoolName;
        this.location = location;
        this.motto = motto;
        this.yearEstablished = yearEstablished;
        this.affiliation = affiliation;
        this.contactInfo = contactInfo;
        this.isActive = isActive;
        this.students = students != null ? students : new ArrayList<>();
        this.teachers = teachers != null ? teachers : new ArrayList<>();
        this.subjects = subjects != null ? subjects : new ArrayList<>();
        this.facilities = facilities != null ? facilities : new ArrayList<>();
    }
}

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
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a lesson that is taught by a teacher on a specific subject.
 */
@Entity
@Getter
@Setter
public class Lesson {

    /**
     * Unique identifier for the lesson.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The subject associated with the lesson.
     */
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    @NotNull(message = "Subject must not be null")
    private Subject subject;

    /**
     * The teacher who conducts the lesson.
     */
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    @NotNull(message = "Teacher must not be null")
    private Teacher teacher;

    /**
     * The start time of the lesson.
     */
    @NotNull(message = "Start time must not be null")
    @Future(message = "Start time must be in the future")
    private LocalDateTime startTime;

    /**
     * The end time of the lesson.
     */
    @NotNull(message = "End time must not be null")
    private LocalDateTime endTime;

    /**
     * The list of students enrolled in the lesson.
     */
    @ManyToMany
    @JoinTable(
            name = "lesson_students",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    // No-argument constructor
    public Lesson() {
    }

    @Builder
    public Lesson(LocalDateTime startTime, LocalDateTime endTime, Subject subject, Teacher teacher, List<Student> students) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject != null ? subject : new Subject();
        this.teacher = teacher != null ? teacher : new Teacher();
        this.students = students != null ? students : new ArrayList<>();
    }
}

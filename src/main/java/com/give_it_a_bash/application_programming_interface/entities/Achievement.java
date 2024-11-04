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

import java.time.LocalDate;

/**
 * Represents an achievement earned by a student.
 */
@Entity
@Getter
@Setter
@Builder
public class Achievement {

    /**
     * Unique identifier for the achievement.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the achievement.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Description of the achievement.
     */
    @Column(nullable = false)
    private String description;

    /**
     * Date when the achievement was awarded.
     */
    @Column(nullable = false)
    private LocalDate dateAwarded;

    /**
     * The student who earned the achievement.
     */
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    /**
     * The individual or organization that awarded the achievement.
     */
    @Column(nullable = false)
    private String awardedBy;

    /**
     * The category of the subject related to the achievement.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SubjectCategory category;
}

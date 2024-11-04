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

/**
 * Represents the various statuses that a student can have within the
 * educational institution. This helps in tracking the progression and
 * current state of students.
 */
public enum Status {
    /**
     * Indicates that the student is currently active and enrolled.
     */
    ACTIVE,

    /**
     * Indicates that the student has been temporarily suspended from
     * classes or school activities.
     */
    SUSPENDED,

    /**
     * Indicates that the student has successfully completed their
     * course of study and graduated.
     */
    GRADUATED,

    /**
     * Indicates that the student has voluntarily left the institution
     * before completing their course.
     */
    WITHDRAWN,

    /**
     * Indicates that the student is no longer enrolled but is still
     * considered part of the school's community.
     */
    ALUMNUS,

    /**
     * Indicates that the student has transferred to another institution.
     */
    TRANSFERRED,

    /**
     * Indicates that the student is no longer active or enrolled in the
     * institution.
     */
    INACTIVE
}

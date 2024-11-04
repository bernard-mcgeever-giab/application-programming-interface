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

import com.give_it_a_bash.application_programming_interface.testData.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link Student} entity.
 */
public class StudentTest {

    private Student student;

    /**
     * Sets up an instance of {@link Student} with sample data before each test.
     */
    @BeforeEach
    public void setUp() {
        student = TestDataHelper.getSTUDENT();
    }

    /**
     * Tests individual properties of the {@link Student} entity.
     */
    @Test
    public void testStudentProperties() {
        assertThat(student.getFirstName()).isEqualTo("Jean");
        assertThat(student.getLastName()).isEqualTo("Grey");
        assertThat(student.getAlias()).isEqualTo("Phoenix");
        assertThat(student.getGuardianFirstName()).isEqualTo("John");
        assertThat(student.getGuardianLastName()).isEqualTo("Grey");
        assertThat(student.getGuardianContactNumber()).isEqualTo("+1-555-0101");
        assertThat(student.getGuardianEmail()).isEqualTo("john.grey@example.com");
        assertThat(student.getContactNumber()).isEqualTo("+1-555-0202");
        assertThat(student.getEmail()).isEqualTo("jean.grey@example.com");
        assertThat(student.getStatus()).isEqualTo(Status.ACTIVE);
        assertThat(student.getIsActive()).isTrue();
        assertThat(student.getLessons()).containsExactly(TestDataHelper.getLESSON());
        assertThat(student.getAchievements()).containsExactly(TestDataHelper.getACHIEVEMENT());
        assertThat(student.getMissionHistory()).containsExactly("Mission X");
        assertThat(student.getPower()).isEqualTo(TestDataHelper.getPOWER());
        assertThat(student.getSchoolData()).isEqualTo(TestDataHelper.getSCHOOL_DATA());
    }
}

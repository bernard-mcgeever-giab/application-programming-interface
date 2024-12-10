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

import com.give_it_a_bash.application_programming_interface.testData.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link Teacher} entity.
 */
public class TeacherTest {

    private Teacher teacher;

    /**
     * Sets up an instance of {@link Teacher} with sample data before each test.
     */
    @BeforeEach
    public void setUp() {
        TestDataHelper.reset();
        teacher = TestDataHelper.getTEACHER();
    }

    /**
     * Tests individual properties of the {@link Teacher} entity.
     */
    @Test
    public void testTeacherProperties() {
        assertThat(teacher.getFirstName()).isEqualTo("Charles");
        assertThat(teacher.getLastName()).isEqualTo("Xavier");
        assertThat(teacher.getAlias()).isEqualTo("Professor X");
        assertThat(teacher.getEmail()).isEqualTo("professor.xavier@example.com");
        assertThat(teacher.getPhoneNumber()).isEqualTo("+1-555-0303");
        assertThat(teacher.getAddress()).isEqualTo("1407 Graymalkin Lane, Salem Center, NY");
        assertThat(teacher.getQualifications()).isEqualTo("PhD in Genetics, Mutant Studies");
        assertThat(teacher.getYearsOfExperience()).isEqualTo(20);
        assertThat(teacher.getDepartment()).isEqualTo("Psychic Studies");
        assertThat(teacher.getIsActive()).isTrue();
        assertThat(teacher.getMissionHistory()).containsExactly("Formed the X-Men");
    }
}

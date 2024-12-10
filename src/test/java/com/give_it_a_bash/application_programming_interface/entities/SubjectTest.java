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

public class SubjectTest {

    private Subject subject;

    /**
     * Sets up instances of {@link Subject} with sample data before each test.
     */
    @BeforeEach
    public void setUp() {
        TestDataHelper.reset();
        subject = TestDataHelper.getSUBJECT();
    }

    /**
     * Tests individual properties of the {@link Subject} entity.
     */
    @Test
    public void testSubjectProperties() {
        assertThat(subject.getSchoolData()).isEqualTo(TestDataHelper.getSCHOOL_DATA());
        assertThat(subject.getName()).isEqualTo("Psychic Studies");
        assertThat(subject.getTeacher()).containsExactly(TestDataHelper.getTEACHER());
        assertThat(subject.getLessons()).containsExactly(TestDataHelper.getLESSON());
    }
}

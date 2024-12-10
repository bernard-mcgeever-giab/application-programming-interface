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

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link Lesson} entity.
 */
public class LessonTest {

    private Lesson lesson;

    /**
     * Initializes mock data for testing.
     */
    @BeforeEach
    public void setUp() {
        TestDataHelper.reset();
        lesson = TestDataHelper.getLESSON();
    }

    /**
     * Tests that a valid {@link Lesson} can be created successfully.
     */
    @Test
    public void testValidLessonCreation() {
        assertThat(lesson).isNotNull();
        assertThat(lesson.getSubject()).isEqualTo(TestDataHelper.getSUBJECT());
        assertThat(lesson.getTeacher()).isEqualTo(TestDataHelper.getTEACHER());
        assertThat(lesson.getStartTime()).isEqualTo(LocalDateTime.of(2024, 11, 1, 10, 0));
        assertThat(lesson.getEndTime()).isEqualTo(LocalDateTime.of(2024, 11, 1, 11, 30));
        assertThat(lesson.getStudents()).containsExactly(TestDataHelper.getSTUDENT());
    }
}

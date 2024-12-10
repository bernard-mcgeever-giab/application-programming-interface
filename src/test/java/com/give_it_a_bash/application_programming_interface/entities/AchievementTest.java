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

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link Achievement} entity class.
 */
public class AchievementTest {

    private Achievement achievement;

    /**
     * Sets up an instance of {@link Achievement} with sample data before each test.
     */
    @BeforeEach
    public void setUp() {
        TestDataHelper.reset();
        achievement = TestDataHelper.getACHIEVEMENT();
    }

    /**
     * Tests the properties of the {@link Achievement} entity.
     */
    @Test
    public void testAchievementProperties() {
        assertThat(achievement.getId()).isNull();
        assertThat(achievement.getTitle()).isEqualTo("Outstanding Contribution");
        assertThat(achievement.getDescription()).isEqualTo("Awarded for innovative contributions to the annual school science fair, showcasing practical applications of scientific concepts.");
        assertThat(achievement.getDateAwarded()).isEqualTo(LocalDate.of(2021, 5, 21));
        assertThat(achievement.getStudent()).isEqualTo(TestDataHelper.getSTUDENT());
        assertThat(achievement.getAwardedBy()).isEqualTo("Professor Hank McCoy");
        assertThat(achievement.getCategory()).isEqualTo(SubjectCategory.BIOLOGY);
    }
}

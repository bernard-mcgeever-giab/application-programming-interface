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
 * Unit tests for the {@link SchoolData} entity class.
 */
public class SchoolDataTest {

    private SchoolData schoolData;

    /**
     * Sets up an instance of {@link SchoolData} with sample data before each test.
     */
    @BeforeEach
    public void setUp() {
        TestDataHelper.reset();
        schoolData = TestDataHelper.getSCHOOL_DATA();
    }

    /**
     * Tests the properties of the {@link SchoolData} entity.
     */
    @Test
    public void testSchoolDataProperties() {
        assertThat(schoolData.getSchoolName()).isEqualTo("Xavier Institute for Higher Learning");
        assertThat(schoolData.getLocation()).isEqualTo("1407 Graymalkin Lane, Salem Center, NY");
        assertThat(schoolData.getMotto()).isEqualTo("Mutatis Mutandis");
        assertThat(schoolData.getYearEstablished()).isEqualTo(1963);
        assertThat(schoolData.getAffiliation()).isEqualTo("Mutant Education and Research");
        assertThat(schoolData.getContactInfo()).isEqualTo("+1-555-XAVIER");
        assertThat(schoolData.isActive()).isTrue();
        assertThat(schoolData.getStudents()).containsExactly(TestDataHelper.getSTUDENT());
        assertThat(schoolData.getTeachers()).containsExactly(TestDataHelper.getTEACHER());
        assertThat(schoolData.getSubjects()).containsExactly(TestDataHelper.getSUBJECT());
        assertThat(schoolData.getFacilities()).containsExactly(TestDataHelper.getFACILITY());
    }
}

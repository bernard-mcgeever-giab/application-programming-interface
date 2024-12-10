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
 * Unit tests for the {@link Facility} class.
 */
public class FacilityTest {

    private Facility facility;

    /**
     * Initializes sample data for testing.
     */
    @BeforeEach
    public void setUp() {
        TestDataHelper.reset();
        facility = TestDataHelper.getFACILITY();
    }

    /**
     * Tests that the facility properties are correctly set and retrieved.
     */
    @Test
    public void testFacilityProperties() {
        assertThat(facility.getName()).isEqualTo("Training Room");
        assertThat(facility.getType()).isEqualTo(FacilityType.CLASSROOM);
        assertThat(facility.getDescription()).isEqualTo("A room equipped for training and practice.");
        assertThat(facility.isAccessible()).isTrue();
        assertThat(facility.getLocationWithinCampus()).isEqualTo("Main Building, 2nd Floor");
        assertThat(facility.getCapacity()).isEqualTo(30);
        assertThat(facility.getSchoolData()).isEqualTo(TestDataHelper.getSCHOOL_DATA());
        assertThat(facility.isOperational()).isTrue();
    }
}

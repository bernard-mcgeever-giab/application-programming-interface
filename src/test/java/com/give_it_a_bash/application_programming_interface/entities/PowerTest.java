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
 * Unit tests for the {@link Power} embeddable class.
 */
public class PowerTest {

    private Power power;

    /**
     * Initializes sample data for testing.
     */
    @BeforeEach
    public void setUp() {
        TestDataHelper.reset();
        power = TestDataHelper.getPOWER();
    }

    /**
     * Tests that {@link Power} properties are correctly set and retrieved.
     */
    @Test
    public void testPowerProperties() {
        assertThat(power.getName()).isEqualTo("Telekinesis");
        assertThat(power.getPowerLevel()).isEqualTo(10);
        assertThat(power.getDescription()).isEqualTo("Enables the user to mentally manipulate and move objects without physical contact.");
        assertThat(power.getCategory()).isEqualTo("Psychic");
        assertThat(power.isActive()).isTrue();
        assertThat(power.getControlLevel()).isEqualTo(10);
        assertThat(power.getOriginSource()).isEqualTo(PowerSource.GENETIC_MUTATION);
    }
}

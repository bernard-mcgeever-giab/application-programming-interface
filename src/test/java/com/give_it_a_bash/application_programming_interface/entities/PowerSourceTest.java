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

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for the {@link PowerSource} enum.
 */
public class PowerSourceTest {

    /**
     * Tests that the {@link PowerSource} enum contains the expected values.
     */
    @Test
    public void testEnumValues() {
        assertThat(PowerSource.values()).containsExactly(
                PowerSource.GENETIC_MUTATION,
                PowerSource.TECHNOLOGY
        );
    }

    /**
     * Tests individual power sources to confirm they are correctly initialized.
     */
    @Test
    public void testIndividualPowerSource() {
        assertThat(PowerSource.valueOf("GENETIC_MUTATION")).isEqualTo(PowerSource.GENETIC_MUTATION);
        assertThat(PowerSource.valueOf("TECHNOLOGY")).isEqualTo(PowerSource.TECHNOLOGY);
    }

    /**
     * Tests that each {@link PowerSource} constant has an ordinal value that matches its order.
     */
    @Test
    public void testPowerSourceOrdinals() {
        assertThat(PowerSource.GENETIC_MUTATION.ordinal()).isEqualTo(0);
        assertThat(PowerSource.TECHNOLOGY.ordinal()).isEqualTo(1);
    }
}

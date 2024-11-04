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
 * Unit tests for the {@link Status} enum.
 */
public class StatusTest {

    /**
     * Tests that the {@link Status} enum contains the expected values.
     */
    @Test
    public void testEnumValues() {
        assertThat(Status.values()).containsExactly(
                Status.ACTIVE,
                Status.SUSPENDED,
                Status.GRADUATED,
                Status.WITHDRAWN,
                Status.ALUMNUS,
                Status.TRANSFERRED,
                Status.INACTIVE
        );
    }

    /**
     * Tests individual statuses to confirm they are correctly initialized.
     */
    @Test
    public void testIndividualStatus() {
        assertThat(Status.valueOf("ACTIVE")).isEqualTo(Status.ACTIVE);
        assertThat(Status.valueOf("SUSPENDED")).isEqualTo(Status.SUSPENDED);
        assertThat(Status.valueOf("GRADUATED")).isEqualTo(Status.GRADUATED);
        assertThat(Status.valueOf("WITHDRAWN")).isEqualTo(Status.WITHDRAWN);
        assertThat(Status.valueOf("ALUMNUS")).isEqualTo(Status.ALUMNUS);
        assertThat(Status.valueOf("TRANSFERRED")).isEqualTo(Status.TRANSFERRED);
        assertThat(Status.valueOf("INACTIVE")).isEqualTo(Status.INACTIVE);
    }

    /**
     * Tests that each {@link Status} constant has an ordinal value that matches its order.
     */
    @Test
    public void testStatusOrdinals() {
        assertThat(Status.ACTIVE.ordinal()).isEqualTo(0);
        assertThat(Status.SUSPENDED.ordinal()).isEqualTo(1);
        assertThat(Status.GRADUATED.ordinal()).isEqualTo(2);
        assertThat(Status.WITHDRAWN.ordinal()).isEqualTo(3);
        assertThat(Status.ALUMNUS.ordinal()).isEqualTo(4);
        assertThat(Status.TRANSFERRED.ordinal()).isEqualTo(5);
        assertThat(Status.INACTIVE.ordinal()).isEqualTo(6);
    }
}

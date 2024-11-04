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
 * Unit tests for the {@link FacilityType} enum.
 */
public class FacilityTypeTest {

    /**
     * Tests that the {@link FacilityType} enum contains all expected constants.
     */
    @Test
    public void testFacilityTypeEnumValues() {
        assertThat(FacilityType.values()).containsExactly(
                FacilityType.CLASSROOM,
                FacilityType.GYM,
                FacilityType.LIBRARY,
                FacilityType.LABORATORY,
                FacilityType.AUDITORIUM,
                FacilityType.TRAINING_CENTER,
                FacilityType.CAFETERIA,
                FacilityType.DORMITORY,
                FacilityType.COMPUTER_LAB,
                FacilityType.MEDICAL_CENTER,
                FacilityType.RECREATION_ROOM,
                FacilityType.ADMINISTRATION_OFFICE,
                FacilityType.ART_STUDIO,
                FacilityType.MUSIC_ROOM,
                FacilityType.COUNSELING_CENTER,
                FacilityType.SPORTS_FIELD,
                FacilityType.SWIMMING_POOL,
                FacilityType.LAB_FACILITY,
                FacilityType.WORKSHOP,
                FacilityType.GREENHOUSE,
                FacilityType.CHAPEL
        );
    }

    /**
     * Tests that each {@link FacilityType} constant has the correct name.
     */
    @Test
    public void testFacilityTypeNames() {
        assertThat(FacilityType.CLASSROOM.name()).isEqualTo("CLASSROOM");
        assertThat(FacilityType.GYM.name()).isEqualTo("GYM");
        assertThat(FacilityType.LIBRARY.name()).isEqualTo("LIBRARY");
        assertThat(FacilityType.LABORATORY.name()).isEqualTo("LABORATORY");
        assertThat(FacilityType.AUDITORIUM.name()).isEqualTo("AUDITORIUM");
        assertThat(FacilityType.TRAINING_CENTER.name()).isEqualTo("TRAINING_CENTER");
        assertThat(FacilityType.CAFETERIA.name()).isEqualTo("CAFETERIA");
        assertThat(FacilityType.DORMITORY.name()).isEqualTo("DORMITORY");
        assertThat(FacilityType.COMPUTER_LAB.name()).isEqualTo("COMPUTER_LAB");
        assertThat(FacilityType.MEDICAL_CENTER.name()).isEqualTo("MEDICAL_CENTER");
        assertThat(FacilityType.RECREATION_ROOM.name()).isEqualTo("RECREATION_ROOM");
        assertThat(FacilityType.ADMINISTRATION_OFFICE.name()).isEqualTo("ADMINISTRATION_OFFICE");
        assertThat(FacilityType.ART_STUDIO.name()).isEqualTo("ART_STUDIO");
        assertThat(FacilityType.MUSIC_ROOM.name()).isEqualTo("MUSIC_ROOM");
        assertThat(FacilityType.COUNSELING_CENTER.name()).isEqualTo("COUNSELING_CENTER");
        assertThat(FacilityType.SPORTS_FIELD.name()).isEqualTo("SPORTS_FIELD");
        assertThat(FacilityType.SWIMMING_POOL.name()).isEqualTo("SWIMMING_POOL");
        assertThat(FacilityType.LAB_FACILITY.name()).isEqualTo("LAB_FACILITY");
        assertThat(FacilityType.WORKSHOP.name()).isEqualTo("WORKSHOP");
        assertThat(FacilityType.GREENHOUSE.name()).isEqualTo("GREENHOUSE");
        assertThat(FacilityType.CHAPEL.name()).isEqualTo("CHAPEL");
    }

    /**
     * Tests that each {@link FacilityType} constant has an ordinal value that matches its order.
     */
    @Test
    public void testFacilityTypeOrdinals() {
        assertThat(FacilityType.CLASSROOM.ordinal()).isEqualTo(0);
        assertThat(FacilityType.GYM.ordinal()).isEqualTo(1);
        assertThat(FacilityType.LIBRARY.ordinal()).isEqualTo(2);
        assertThat(FacilityType.LABORATORY.ordinal()).isEqualTo(3);
        assertThat(FacilityType.AUDITORIUM.ordinal()).isEqualTo(4);
        assertThat(FacilityType.TRAINING_CENTER.ordinal()).isEqualTo(5);
        assertThat(FacilityType.CAFETERIA.ordinal()).isEqualTo(6);
        assertThat(FacilityType.DORMITORY.ordinal()).isEqualTo(7);
        assertThat(FacilityType.COMPUTER_LAB.ordinal()).isEqualTo(8);
        assertThat(FacilityType.MEDICAL_CENTER.ordinal()).isEqualTo(9);
        assertThat(FacilityType.RECREATION_ROOM.ordinal()).isEqualTo(10);
        assertThat(FacilityType.ADMINISTRATION_OFFICE.ordinal()).isEqualTo(11);
        assertThat(FacilityType.ART_STUDIO.ordinal()).isEqualTo(12);
        assertThat(FacilityType.MUSIC_ROOM.ordinal()).isEqualTo(13);
        assertThat(FacilityType.COUNSELING_CENTER.ordinal()).isEqualTo(14);
        assertThat(FacilityType.SPORTS_FIELD.ordinal()).isEqualTo(15);
        assertThat(FacilityType.SWIMMING_POOL.ordinal()).isEqualTo(16);
        assertThat(FacilityType.LAB_FACILITY.ordinal()).isEqualTo(17);
        assertThat(FacilityType.WORKSHOP.ordinal()).isEqualTo(18);
        assertThat(FacilityType.GREENHOUSE.ordinal()).isEqualTo(19);
        assertThat(FacilityType.CHAPEL.ordinal()).isEqualTo(20);
    }

}

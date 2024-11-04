/*
 * Copyright (c) 2024 Give It A Bash
 *
 * This file is part of Give It A Bash proprietary software.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 * Proprietary and confidential.
 *
 * Created and maintained by Give It A Bash.
 */

package com.give_it_a_bash.application_programming_interface.services;

import com.give_it_a_bash.application_programming_interface.entities.Facility;
import com.give_it_a_bash.application_programming_interface.entities.FacilityType;
import com.give_it_a_bash.application_programming_interface.repositories.FacilityRepository;
import com.give_it_a_bash.application_programming_interface.testData.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link FacilityService} class.
 * <p>
 * These tests cover the CRUD operations provided by FacilityService.
 * Each test verifies the expected behavior when interacting with the {@link FacilityRepository}.
 * </p>
 */
class FacilityServiceTest {

    @Mock
    private FacilityRepository facilityRepository;

    @InjectMocks
    private FacilityService facilityService;

    private Facility facility;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        facility = TestDataHelper.getFACILITY();
    }

    /**
     * Tests the creation of a new {@link Facility} entry.
     * Verifies that the facility is saved in the repository.
     */
    @Test
    void testCreateFacility() {
        when(facilityRepository.save(any(Facility.class))).thenReturn(facility);

        Facility createdFacility = facilityService.createFacility(facility);

        assertThat(createdFacility).isEqualTo(facility);
        verify(facilityRepository, times(1)).save(facility);
    }

    /**
     * Tests retrieval of all {@link Facility} entries.
     * Verifies that the service retrieves the expected list of facilities from the repository.
     */
    @Test
    void testGetAllFacilities() {
        List<Facility> facilities = Collections.singletonList(facility);
        when(facilityRepository.findAll()).thenReturn(facilities);

        List<Facility> result = facilityService.getAllFacilities();

        assertThat(result).containsExactly(facility);
        verify(facilityRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of a {@link Facility} by its ID.
     * Verifies that the correct facility is returned when found.
     */
    @Test
    void testGetFacilityById() {
        when(facilityRepository.findById(anyLong())).thenReturn(Optional.of(facility));

        Optional<Facility> result = facilityService.getFacilityById(1L);

        assertThat(result).isPresent().contains(facility);
        verify(facilityRepository, times(1)).findById(1L);
    }

    /**
     * Tests retrieval of a {@link Facility} by a non-existent ID.
     * Verifies that an empty Optional is returned when the ID is not found.
     */
    @Test
    void testGetFacilityById_NotFound() {
        when(facilityRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Facility> result = facilityService.getFacilityById(1L);

        assertThat(result).isEmpty();
        verify(facilityRepository, times(1)).findById(1L);
    }

    /**
     * Tests updating an existing {@link Facility} entry.
     * Verifies that the facility is updated with the new details provided.
     */
    @Test
    void testUpdateFacility() {
        Facility updatedDetails = TestDataHelper.createFacility("Research Lab",
                FacilityType.LABORATORY,
                "A state-of-the-art facility equipped for advanced mutant research and experiments.",
                false,
                "West Wing, Ground Floor",
                15,
                false);

        when(facilityRepository.findById(anyLong())).thenReturn(Optional.of(facility));
        when(facilityRepository.save(any(Facility.class))).thenReturn(updatedDetails);

        Facility updatedFacility = facilityService.updateFacility(1L, updatedDetails);

        assertThat(updatedFacility.getName()).isEqualTo(updatedDetails.getName());
        assertThat(updatedFacility.getType()).isEqualTo(updatedDetails.getType());
        assertThat(updatedFacility.getDescription()).isEqualTo(updatedDetails.getDescription());
        assertThat(updatedFacility.isAccessible()).isEqualTo(updatedDetails.isAccessible());
        assertThat(updatedFacility.getLocationWithinCampus()).isEqualTo(updatedDetails.getLocationWithinCampus());
        assertThat(updatedFacility.getCapacity()).isEqualTo(updatedDetails.getCapacity());
        assertThat(updatedFacility.isOperational()).isEqualTo(updatedDetails.isOperational());
        verify(facilityRepository, times(1)).findById(1L);
        verify(facilityRepository, times(1)).save(facility);
    }

    /**
     * Tests updating a {@link Facility} with a non-existent ID.
     * Verifies that a {@link RuntimeException} is thrown when the facility is not found.
     */
    @Test
    void testUpdateFacility_NotFound() {
        when(facilityRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> facilityService.updateFacility(1L, facility))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Facility not found with id 1");
        verify(facilityRepository, times(1)).findById(1L);
        verify(facilityRepository, never()).save(any(Facility.class));
    }

    /**
     * Tests the deletion of a {@link Facility} entry by its ID.
     * Verifies that the facility is deleted from the repository.
     */
    @Test
    void testDeleteFacility() {
        doNothing().when(facilityRepository).deleteById(anyLong());

        facilityService.deleteFacility(1L);

        verify(facilityRepository, times(1)).deleteById(1L);
    }
}

/*
 * Copyright (c) 2024 Give It A Bash
 *
 * This file is part of Give It A Bash proprietary software.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 * Proprietary and confidential.
 *
 * Created and maintained by Give It A Bash.
 */

package com.give_it_a_bash.application_programming_interface.web;

import com.give_it_a_bash.application_programming_interface.entities.Facility;
import com.give_it_a_bash.application_programming_interface.entities.FacilityType;
import com.give_it_a_bash.application_programming_interface.services.FacilityService;
import com.give_it_a_bash.application_programming_interface.testData.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the FacilityController class.
 * This class tests the RESTful endpoints for creating, retrieving, updating, and deleting Facility entities.
 */
class FacilityControllerTest {

    @Mock
    private FacilityService facilityService;

    @InjectMocks
    private FacilityController facilityController;

    private Facility facility;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        facility = TestDataHelper.getFACILITY();
    }

    /**
     * Tests the createFacility() method.
     * Verifies that a new Facility is created successfully and returns the correct HTTP status.
     */
    @Test
    void createFacility_ReturnsCreatedFacility() {
        when(facilityService.createFacility(any(Facility.class))).thenReturn(facility);

        ResponseEntity<Facility> response = facilityController.createFacility(facility);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(facility, response.getBody());
        verify(facilityService, times(1)).createFacility(facility);
    }

    /**
     * Tests the getAllFacilities() method.
     * Verifies that all Facilities are retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getAllFacilities_ReturnsListOfFacilities() {
        List<Facility> facilities = Collections.singletonList(facility);
        when(facilityService.getAllFacilities()).thenReturn(facilities);

        ResponseEntity<List<Facility>> response = facilityController.getAllFacilities();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facilities, response.getBody());
        verify(facilityService, times(1)).getAllFacilities();
    }

    /**
     * Tests the getFacilityById() method for an existing ID.
     * Verifies that the Facility is retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getFacilityById_ExistingId_ReturnsFacility() {
        when(facilityService.getFacilityById(1L)).thenReturn(Optional.of(facility));

        ResponseEntity<Facility> response = facilityController.getFacilityById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(facility, response.getBody());
        verify(facilityService, times(1)).getFacilityById(1L);
    }

    /**
     * Tests the getFacilityById() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Facility is not found.
     */
    @Test
    void getFacilityById_NonExistingId_ReturnsNotFound() {
        when(facilityService.getFacilityById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Facility> response = facilityController.getFacilityById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(facilityService, times(1)).getFacilityById(1L);
    }

    /**
     * Tests the updateFacility() method for an existing ID.
     * Verifies that an existing Facility is updated successfully and returns the correct HTTP status.
     */
    @Test
    void updateFacility_ExistingId_ReturnsUpdatedFacility() {
        Facility updatedFacility = TestDataHelper.createFacility("Research Lab",
                FacilityType.LABORATORY,
                "A state-of-the-art facility equipped for advanced mutant research and experiments.",
                false,
                "West Wing, Ground Floor",
                15,
                false);
        when(facilityService.updateFacility(eq(1L), any(Facility.class))).thenReturn(updatedFacility);

        ResponseEntity<Facility> response = facilityController.updateFacility(1L, facility);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedFacility, response.getBody());
        verify(facilityService, times(1)).updateFacility(1L, facility);
    }

    /**
     * Tests the updateFacility() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Facility is not found.
     */
    @Test
    void updateFacility_NonExistingId_ReturnsNotFound() {
        when(facilityService.updateFacility(eq(1L), any(Facility.class)))
                .thenThrow(new RuntimeException("Facility not found with id 1"));

        ResponseEntity<Facility> response = facilityController.updateFacility(1L, facility);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(facilityService, times(1)).updateFacility(eq(1L), any(Facility.class));
    }

    /**
     * Tests the deleteFacility() method for an existing ID.
     * Verifies that an existing Facility is deleted successfully and returns the correct HTTP status.
     */
    @Test
    void deleteFacility_ExistingId_ReturnsNoContent() {
        doNothing().when(facilityService).deleteFacility(1L);

        ResponseEntity<Void> response = facilityController.deleteFacility(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(facilityService, times(1)).deleteFacility(1L);
    }

    /**
     * Tests the deleteFacility() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Facility is not found.
     */
    @Test
    void deleteFacility_NonExistingId_ReturnsNotFound() {
        doThrow(new RuntimeException("Facility not found")).when(facilityService).deleteFacility(1L);

        ResponseEntity<Void> response = facilityController.deleteFacility(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(facilityService, times(1)).deleteFacility(1L);
    }
}

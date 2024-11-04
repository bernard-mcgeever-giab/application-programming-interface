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

import com.give_it_a_bash.application_programming_interface.entities.SchoolData;
import com.give_it_a_bash.application_programming_interface.services.SchoolDataService;
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
 * Unit tests for the SchoolDataController class.
 * This class tests the RESTful endpoints for creating, retrieving, updating, and deleting SchoolData entities.
 */
class SchoolDataControllerTest {

    @Mock
    private SchoolDataService schoolDataService;

    @InjectMocks
    private SchoolDataController schoolDataController;

    private SchoolData schoolData;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        schoolData = TestDataHelper.getSCHOOL_DATA();
    }

    /**
     * Tests the createSchoolData() method.
     * Verifies that a new SchoolData is created successfully and returns the correct HTTP status.
     */
    @Test
    void createSchoolData_ReturnsCreatedSchoolData() {
        when(schoolDataService.createSchoolData(any(SchoolData.class))).thenReturn(schoolData);

        ResponseEntity<SchoolData> response = schoolDataController.createSchoolData(schoolData);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(schoolData, response.getBody());
        verify(schoolDataService, times(1)).createSchoolData(schoolData);
    }

    /**
     * Tests the getAllSchoolData() method.
     * Verifies that all SchoolData entries are retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getAllSchoolData_ReturnsListOfSchoolData() {
        List<SchoolData> schoolDataList = Collections.singletonList(schoolData);
        when(schoolDataService.getAllSchoolData()).thenReturn(schoolDataList);

        ResponseEntity<List<SchoolData>> response = schoolDataController.getAllSchoolData();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(schoolDataList, response.getBody());
        verify(schoolDataService, times(1)).getAllSchoolData();
    }

    /**
     * Tests the getSchoolDataById() method for an existing ID.
     * Verifies that the SchoolData is retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getSchoolDataById_ExistingId_ReturnsSchoolData() {
        when(schoolDataService.getSchoolDataById(1L)).thenReturn(Optional.of(schoolData));

        ResponseEntity<SchoolData> response = schoolDataController.getSchoolDataById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(schoolData, response.getBody());
        verify(schoolDataService, times(1)).getSchoolDataById(1L);
    }

    /**
     * Tests the getSchoolDataById() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the SchoolData is not found.
     */
    @Test
    void getSchoolDataById_NonExistingId_ReturnsNotFound() {
        when(schoolDataService.getSchoolDataById(1L)).thenReturn(Optional.empty());

        ResponseEntity<SchoolData> response = schoolDataController.getSchoolDataById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(schoolDataService, times(1)).getSchoolDataById(1L);
    }

    /**
     * Tests the updateSchoolData() method for an existing ID.
     * Verifies that an existing SchoolData is updated successfully and returns the correct HTTP status.
     */
    @Test
    void updateSchoolData_ExistingId_ReturnsUpdatedSchoolData() {
        SchoolData updatedSchoolData = TestDataHelper.createSchoolData("Mutant Academy for Gifted Students",
                "1234 Elm Street, Westchester, NY",
                "Evolving Together",
                1975,
                "Mutant Research and Development",
                "+1-555-MUTANT",
                true);
        when(schoolDataService.updateSchoolData(eq(1L), any(SchoolData.class))).thenReturn(updatedSchoolData);

        ResponseEntity<SchoolData> response = schoolDataController.updateSchoolData(1L, schoolData);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSchoolData, response.getBody());
        verify(schoolDataService, times(1)).updateSchoolData(1L, schoolData);
    }

    /**
     * Tests the updateSchoolData() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the SchoolData is not found.
     */
    @Test
    void updateSchoolData_NonExistingId_ReturnsNotFound() {
        when(schoolDataService.updateSchoolData(eq(1L), any(SchoolData.class)))
                .thenThrow(new RuntimeException("SchoolData not found with id 1")); // Consider a specific exception type

        ResponseEntity<SchoolData> response = schoolDataController.updateSchoolData(1L, schoolData);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(schoolDataService, times(1)).updateSchoolData(eq(1L), any(SchoolData.class));
    }

    /**
     * Tests the deleteSchoolData() method for an existing ID.
     * Verifies that an existing SchoolData is deleted successfully and returns the correct HTTP status.
     */
    @Test
    void deleteSchoolData_ExistingId_ReturnsNoContent() {
        doNothing().when(schoolDataService).deleteSchoolData(1L);

        ResponseEntity<Void> response = schoolDataController.deleteSchoolData(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(schoolDataService, times(1)).deleteSchoolData(1L);
    }

    /**
     * Tests the deleteSchoolData() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the SchoolData is not found.
     */
    @Test
    void deleteSchoolData_NonExistingId_ReturnsNotFound() {
        doThrow(new RuntimeException("SchoolData not found")).when(schoolDataService).deleteSchoolData(1L);

        ResponseEntity<Void> response = schoolDataController.deleteSchoolData(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(schoolDataService, times(1)).deleteSchoolData(1L);
    }
}

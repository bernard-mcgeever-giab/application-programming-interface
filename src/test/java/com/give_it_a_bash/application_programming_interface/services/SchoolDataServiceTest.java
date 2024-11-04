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

import com.give_it_a_bash.application_programming_interface.entities.SchoolData;
import com.give_it_a_bash.application_programming_interface.repositories.SchoolDataRepository;
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
 * Unit tests for the {@link SchoolDataService} class.
 *
 * These tests cover the CRUD operations provided by SchoolDataService.
 * Each test verifies the expected behavior when interacting with the {@link SchoolDataRepository}.
 */
class SchoolDataServiceTest {

    @Mock
    private SchoolDataRepository schoolDataRepository;

    @InjectMocks
    private SchoolDataService schoolDataService;

    private SchoolData schoolData;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        schoolData = TestDataHelper.getSCHOOL_DATA();
    }

    /**
     * Tests the creation of a new {@link SchoolData} entry.
     * Verifies that the school data is saved in the repository.
     */
    @Test
    void testCreateSchoolData() {
        when(schoolDataRepository.save(any(SchoolData.class))).thenReturn(schoolData);

        SchoolData createdSchoolData = schoolDataService.createSchoolData(schoolData);

        assertThat(createdSchoolData).isEqualTo(schoolData);
        verify(schoolDataRepository, times(1)).save(schoolData);
    }

    /**
     * Tests retrieval of all {@link SchoolData} entries.
     * Verifies that the service retrieves the expected list of school data.
     */
    @Test
    void testGetAllSchoolData() {
        List<SchoolData> schoolDataList = Collections.singletonList(schoolData);
        when(schoolDataRepository.findAll()).thenReturn(schoolDataList);

        List<SchoolData> result = schoolDataService.getAllSchoolData();

        assertThat(result).containsExactly(schoolData);
        verify(schoolDataRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of {@link SchoolData} by its ID.
     * Verifies that the correct school data is returned when found.
     */
    @Test
    void testGetSchoolDataById() {
        when(schoolDataRepository.findById(anyLong())).thenReturn(Optional.of(schoolData));

        Optional<SchoolData> result = schoolDataService.getSchoolDataById(1L);

        assertThat(result).isPresent().contains(schoolData);
        verify(schoolDataRepository, times(1)).findById(1L);
    }

    /**
     * Tests retrieval of {@link SchoolData} by a non-existent ID.
     * Verifies that an empty Optional is returned when the ID is not found.
     */
    @Test
    void testGetSchoolDataById_NotFound() {
        when(schoolDataRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<SchoolData> result = schoolDataService.getSchoolDataById(1L);

        assertThat(result).isEmpty();
        verify(schoolDataRepository, times(1)).findById(1L);
    }

    /**
     * Tests updating an existing {@link SchoolData} entry.
     * Verifies that the school data is updated with the new details provided.
     */
    @Test
    void testUpdateSchoolData() {
        SchoolData updatedDetails = TestDataHelper.createSchoolData("Mutant Academy for Gifted Students",
                "1234 Elm Street, Westchester, NY",
                "Evolving Together",
                1975,
                "Mutant Research and Development",
                "+1-555-MUTANT",
                true);

        when(schoolDataRepository.findById(anyLong())).thenReturn(Optional.of(schoolData));
        when(schoolDataRepository.save(any(SchoolData.class))).thenReturn(updatedDetails);

        SchoolData updatedSchoolData = schoolDataService.updateSchoolData(1L, updatedDetails);

        assertThat(updatedSchoolData.getSchoolName()).isEqualTo(updatedDetails.getSchoolName());
        assertThat(updatedSchoolData.getLocation()).isEqualTo(updatedDetails.getLocation());
        assertThat(updatedSchoolData.getMotto()).isEqualTo(updatedDetails.getMotto());
        assertThat(updatedSchoolData.getYearEstablished()).isEqualTo(updatedDetails.getYearEstablished());
        assertThat(updatedSchoolData.getAffiliation()).isEqualTo(updatedDetails.getAffiliation());
        assertThat(updatedSchoolData.isActive()).isEqualTo(updatedDetails.isActive());
        verify(schoolDataRepository, times(1)).findById(1L);
        verify(schoolDataRepository, times(1)).save(schoolData);
    }

    /**
     * Tests updating a {@link SchoolData} with a non-existent ID.
     * Verifies that a {@link RuntimeException} is thrown when the school data is not found.
     */
    @Test
    void testUpdateSchoolData_NotFound() {
        when(schoolDataRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> schoolDataService.updateSchoolData(1L, schoolData))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("School data not found with id 1");
        verify(schoolDataRepository, times(1)).findById(1L);
        verify(schoolDataRepository, never()).save(any(SchoolData.class));
    }

    /**
     * Tests the deletion of a {@link SchoolData} entry by its ID.
     * Verifies that the school data is deleted from the repository.
     */
    @Test
    void testDeleteSchoolData() {
        doNothing().when(schoolDataRepository).deleteById(anyLong());

        schoolDataService.deleteSchoolData(1L);

        verify(schoolDataRepository, times(1)).deleteById(1L);
    }
}

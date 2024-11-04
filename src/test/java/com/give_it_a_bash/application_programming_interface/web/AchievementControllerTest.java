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

import com.give_it_a_bash.application_programming_interface.entities.Achievement;
import com.give_it_a_bash.application_programming_interface.entities.SubjectCategory;
import com.give_it_a_bash.application_programming_interface.services.AchievementService;
import com.give_it_a_bash.application_programming_interface.testData.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the AchievementController class.
 * This class tests the RESTful endpoints for creating, retrieving, updating, and deleting Achievement entities.
 */
class AchievementControllerTest {

    @Mock
    private AchievementService achievementService;

    @InjectMocks
    private AchievementController achievementController;

    private Achievement achievement;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        achievement = TestDataHelper.getACHIEVEMENT();
    }

    /**
     * Tests the createAchievement() method.
     * Verifies that a new Achievement is created successfully and returns the correct HTTP status.
     */
    @Test
    void createAchievement_ReturnsCreatedAchievement() {
        when(achievementService.createAchievement(any(Achievement.class))).thenReturn(achievement);

        ResponseEntity<Achievement> response = achievementController.createAchievement(achievement);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(achievement, response.getBody());
        verify(achievementService, times(1)).createAchievement(achievement);
    }

    /**
     * Tests the getAllAchievements() method.
     * Verifies that all Achievements are retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getAllAchievements_ReturnsListOfAchievements() {
        List<Achievement> achievements = Collections.singletonList(achievement);
        when(achievementService.getAllAchievements()).thenReturn(achievements);

        ResponseEntity<List<Achievement>> response = achievementController.getAllAchievements();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(achievements, response.getBody());
        verify(achievementService, times(1)).getAllAchievements();
    }

    /**
     * Tests the getAchievementById() method for an existing ID.
     * Verifies that the Achievement is retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getAchievementById_ExistingId_ReturnsAchievement() {
        when(achievementService.getAchievementById(1L)).thenReturn(Optional.of(achievement));

        ResponseEntity<Achievement> response = achievementController.getAchievementById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(achievement, response.getBody());
        verify(achievementService, times(1)).getAchievementById(1L);
    }

    /**
     * Tests the getAchievementById() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Achievement is not found.
     */
    @Test
    void getAchievementById_NonExistingId_ReturnsNotFound() {
        when(achievementService.getAchievementById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Achievement> response = achievementController.getAchievementById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(achievementService, times(1)).getAchievementById(1L);
    }

    /**
     * Tests the updateAchievement() method for an existing ID.
     * Verifies that an existing Achievement is updated successfully and returns the correct HTTP status.
     */
    @Test
    void updateAchievement_ExistingId_ReturnsUpdatedAchievement() {
        Achievement updatedAchievement = TestDataHelper.createAchievement("Exemplary Academic Performance",
                "Recognized for exceptional academic achievements and high grades throughout the semester.",
                LocalDate.of(2023, 9, 15),
                "Dr. Moira MacTaggert",
                SubjectCategory.ACADEMIC);
        when(achievementService.updateAchievement(eq(1L), any(Achievement.class))).thenReturn(updatedAchievement);

        ResponseEntity<Achievement> response = achievementController.updateAchievement(1L, achievement);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedAchievement, response.getBody());
        verify(achievementService, times(1)).updateAchievement(1L, achievement);
    }

    /**
     * Tests the updateAchievement() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Achievement is not found.
     */
    @Test
    void updateAchievement_NonExistingId_ReturnsNotFound() {
        when(achievementService.updateAchievement(eq(1L), any(Achievement.class)))
                .thenThrow(new RuntimeException("Achievement not found with id 1"));

        ResponseEntity<Achievement> response = achievementController.updateAchievement(1L, achievement);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(achievementService, times(1)).updateAchievement(eq(1L), any(Achievement.class));
    }

    /**
     * Tests the deleteAchievement() method for an existing ID.
     * Verifies that an existing Achievement is deleted successfully and returns the correct HTTP status.
     */
    @Test
    void deleteAchievement_ExistingId_ReturnsNoContent() {
        doNothing().when(achievementService).deleteAchievement(1L);

        ResponseEntity<Void> response = achievementController.deleteAchievement(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(achievementService, times(1)).deleteAchievement(1L);
    }

    /**
     * Tests the deleteAchievement() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Achievement is not found.
     */
    @Test
    void deleteAchievement_NonExistingId_ReturnsNotFound() {
        doThrow(new RuntimeException("Achievement not found")).when(achievementService).deleteAchievement(1L);

        ResponseEntity<Void> response = achievementController.deleteAchievement(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(achievementService, times(1)).deleteAchievement(1L);
    }
}

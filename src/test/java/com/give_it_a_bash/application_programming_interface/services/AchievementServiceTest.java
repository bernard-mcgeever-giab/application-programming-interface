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

import com.give_it_a_bash.application_programming_interface.entities.Achievement;
import com.give_it_a_bash.application_programming_interface.entities.SubjectCategory;
import com.give_it_a_bash.application_programming_interface.repositories.AchievementRepository;
import com.give_it_a_bash.application_programming_interface.testData.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link AchievementService} class.
 *
 * These tests cover the CRUD operations provided by AchievementService.
 * Each test verifies the expected behavior when interacting with the {@link AchievementRepository}.
 */
class AchievementServiceTest {

    @Mock
    private AchievementRepository achievementRepository;

    @InjectMocks
    private AchievementService achievementService;

    private Achievement achievement;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        achievement = TestDataHelper.getACHIEVEMENT();
    }

    /**
     * Tests the creation of a new {@link Achievement} entry.
     * Verifies that the achievement is saved in the repository.
     */
    @Test
    void testCreateAchievement() {
        when(achievementRepository.save(any(Achievement.class))).thenReturn(achievement);

        Achievement createdAchievement = achievementService.createAchievement(achievement);

        assertThat(createdAchievement).isEqualTo(achievement);
        verify(achievementRepository, times(1)).save(achievement);
    }

    /**
     * Tests retrieval of all {@link Achievement} entries.
     * Verifies that the service retrieves the expected list of achievements from the repository.
     */
    @Test
    void testGetAllAchievements() {
        List<Achievement> achievements = Collections.singletonList(achievement);
        when(achievementRepository.findAll()).thenReturn(achievements);

        List<Achievement> result = achievementService.getAllAchievements();

        assertThat(result).containsExactly(achievement);
        verify(achievementRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of an {@link Achievement} by its ID.
     * Verifies that the correct achievement is returned when found.
     */
    @Test
    void testGetAchievementById() {
        when(achievementRepository.findById(anyLong())).thenReturn(Optional.of(achievement));

        Optional<Achievement> result = achievementService.getAchievementById(1L);

        assertThat(result).isPresent().contains(achievement);
        verify(achievementRepository, times(1)).findById(1L);
    }

    /**
     * Tests retrieval of an {@link Achievement} by a non-existent ID.
     * Verifies that an empty Optional is returned when the ID is not found.
     */
    @Test
    void testGetAchievementById_NotFound() {
        when(achievementRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Achievement> result = achievementService.getAchievementById(1L);

        assertThat(result).isEmpty();
        verify(achievementRepository, times(1)).findById(1L);
    }

    /**
     * Tests updating an existing {@link Achievement} entry.
     * Verifies that the achievement is updated with the new details provided.
     */
    @Test
    void testUpdateAchievement() {
        Achievement updatedDetails = TestDataHelper.createAchievement("Exemplary Academic Performance",
                "Recognized for exceptional academic achievements and high grades throughout the semester.",
                LocalDate.of(2023, 9, 15),
                "Dr. Moira MacTaggert",
                SubjectCategory.ACADEMIC);

        when(achievementRepository.findById(anyLong())).thenReturn(Optional.of(achievement));
        when(achievementRepository.save(any(Achievement.class))).thenReturn(updatedDetails);

        Achievement updatedAchievement = achievementService.updateAchievement(1L, updatedDetails);

        assertThat(updatedAchievement.getTitle()).isEqualTo(updatedDetails.getTitle());
        assertThat(updatedAchievement.getDescription()).isEqualTo(updatedDetails.getDescription());
        assertThat(updatedAchievement.getDateAwarded()).isEqualTo(updatedDetails.getDateAwarded());
        assertThat(updatedAchievement.getAwardedBy()).isEqualTo(updatedDetails.getAwardedBy());
        verify(achievementRepository, times(1)).findById(1L);
        verify(achievementRepository, times(1)).save(achievement);
    }

    /**
     * Tests updating an {@link Achievement} with a non-existent ID.
     * Verifies that a {@link RuntimeException} is thrown when the achievement is not found.
     */
    @Test
    void testUpdateAchievement_NotFound() {
        when(achievementRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> achievementService.updateAchievement(1L, achievement))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Achievement not found with id 1");
        verify(achievementRepository, times(1)).findById(1L);
        verify(achievementRepository, never()).save(any(Achievement.class));
    }

    /**
     * Tests the deletion of an {@link Achievement} entry by its ID.
     * Verifies that the achievement is deleted from the repository.
     */
    @Test
    void testDeleteAchievement() {
        doNothing().when(achievementRepository).deleteById(anyLong());

        achievementService.deleteAchievement(1L);

        verify(achievementRepository, times(1)).deleteById(1L);
    }
}

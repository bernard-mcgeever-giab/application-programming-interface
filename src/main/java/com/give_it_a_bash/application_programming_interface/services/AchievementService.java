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
import com.give_it_a_bash.application_programming_interface.repositories.AchievementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing achievements earned by students.
 * This class provides methods for creating, retrieving, updating, and deleting achievements.
 */
@Service
public class AchievementService {

    @Autowired
    private AchievementRepository achievementRepository;

    /**
     * Creates a new Achievement entry.
     *
     * @param achievement the Achievement to be created
     * @return the created Achievement
     */
    public Achievement createAchievement(Achievement achievement) {
        return achievementRepository.save(achievement);
    }

    /**
     * Retrieves all Achievement entries.
     *
     * @return a list of all Achievements
     */
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    /**
     * Retrieves Achievement by its ID.
     *
     * @param id the ID of the Achievement
     * @return an Optional containing the Achievement if found
     */
    public Optional<Achievement> getAchievementById(Long id) {
        return achievementRepository.findById(id);
    }

    /**
     * Updates an existing Achievement entry.
     *
     * @param id the ID of the Achievement to be updated
     * @param achievementDetails the new details for the Achievement
     * @return the updated Achievement
     */
    public Achievement updateAchievement(Long id, Achievement achievementDetails) {
        return achievementRepository.findById(id).map(achievement -> {
            achievement.setTitle(achievementDetails.getTitle());
            achievement.setDescription(achievementDetails.getDescription());
            achievement.setDateAwarded(achievementDetails.getDateAwarded());
            achievement.setStudent(achievementDetails.getStudent());
            achievement.setAwardedBy(achievementDetails.getAwardedBy());
            achievement.setCategory(achievementDetails.getCategory());
            return achievementRepository.save(achievement);
        }).orElseThrow(() -> new RuntimeException("Achievement not found with id " + id));
    }

    /**
     * Deletes an Achievement entry by its ID.
     *
     * @param id the ID of the Achievement to be deleted
     */
    public void deleteAchievement(Long id) {
        achievementRepository.deleteById(id);
    }
}

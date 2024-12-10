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
import com.give_it_a_bash.application_programming_interface.services.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling requests related to Achievement.
 * This class exposes RESTful endpoints for creating, retrieving, updating, and deleting achievement entities.
 */
@RestController
@RequestMapping("/api/achievements")
public class AchievementController {

    @Autowired
    private AchievementService achievementService;

    /**
     * Creates a new Achievement entry.
     *
     * @param achievement the Achievement to be created
     * @return ResponseEntity containing the created Achievement and HTTP status
     */
    @PostMapping
    public ResponseEntity<Achievement> createAchievement(@RequestBody Achievement achievement) {
        Achievement createdAchievement = achievementService.createAchievement(achievement);
        return new ResponseEntity<>(createdAchievement, HttpStatus.CREATED);
    }

    /**
     * Retrieves all Achievement entries.
     *
     * @return ResponseEntity containing a list of all Achievements and HTTP status
     */
    @GetMapping
    public ResponseEntity<List<Achievement>> getAllAchievements() {
        List<Achievement> achievements = achievementService.getAllAchievements();
        return new ResponseEntity<>(achievements, HttpStatus.OK);
    }

    /**
     * Retrieves Achievement by its ID.
     *
     * @param id the ID of the Achievement
     * @return ResponseEntity containing the Achievement if found, otherwise a NOT_FOUND status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Achievement> getAchievementById(@PathVariable("id") Long id) {
        Optional<Achievement> achievement = achievementService.getAchievementById(id);
        return achievement.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Updates an existing Achievement entry.
     *
     * @param id the ID of the Achievement to be updated
     * @param achievementDetails the new details for the Achievement
     * @return ResponseEntity containing the updated Achievement and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Achievement> updateAchievement(@PathVariable("id") Long id,
                                                         @RequestBody Achievement achievementDetails) {
        try {
            Achievement updatedAchievement = achievementService.updateAchievement(id, achievementDetails);
            return new ResponseEntity<>(updatedAchievement, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes an Achievement entry by its ID.
     *
     * @param id the ID of the Achievement to be deleted
     * @return ResponseEntity with HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable("id") Long id) {
        try {
            achievementService.deleteAchievement(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

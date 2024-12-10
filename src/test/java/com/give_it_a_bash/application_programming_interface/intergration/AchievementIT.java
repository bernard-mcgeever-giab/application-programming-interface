/*
 * Copyright (c) 2024 Give It A Bash
 *
 * This file is part of Give It A Bash proprietary software.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 * Proprietary and confidential.
 *
 * Created and maintained by Give It A Bash.
 */

package com.give_it_a_bash.application_programming_interface.intergration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.give_it_a_bash.application_programming_interface.entities.*;
import com.give_it_a_bash.application_programming_interface.repositories.AchievementRepository;
import com.give_it_a_bash.application_programming_interface.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the Achievement API endpoints.
 * This class tests CRUD operations for the Achievement entity, including:
 * <ul>
 *     <li>Creating a new Achievement record</li>
 *     <li>Retrieving all Achievement records</li>
 *     <li>Retrieving a specific Achievement record by ID</li>
 *     <li>Updating an existing Achievement record</li>
 *     <li>Deleting an Achievement record</li>
 * </ul>
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:achievement_it;DB_CLOSE_DELAY=-1")
class AchievementIT {

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests

    @Autowired
    private AchievementRepository achievementRepository; // Repository for interacting with the database

    @Autowired
    private StudentRepository studentRepository; // Repository for interacting with Student entity

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    private Achievement achievement; // A class level test instance of Achievement

    private Student student; // A class level test instance of Student

    /**
     * Set up the test environment before each test.
     * This method deletes all records in the database and inserts a sample Achievement record.
     */
    @BeforeEach
    void setUp() {
        achievementRepository.deleteAll();
        studentRepository.deleteAll();

        Power power = Power.builder()
                .name("Telepathy and Telekinesis")
                .description("Telepathic communication and powerful telekinetic abilities, capable of manipulating " +
                        "objects and minds.")
                .category("Psychic")
                .originSource(PowerSource.GENETIC_MUTATION)
                .powerLevel(10)
                .controlLevel(8)
                .isActive(true)
                .build();

        student = Student.builder()
                .firstName("Jean")
                .lastName("Grey")
                .alias("Phoenix")
                .isActive(true)
                .status(Status.ALUMNUS)
                .guardianFirstName("John")
                .guardianLastName("Grey")
                .guardianContactNumber("+1 555-555-5555")
                .guardianEmail("john.grey@example.com")
                .power(power)
                .build();
        student = studentRepository.save(student);

        achievement = Achievement.builder()
                .title("Master of Telepathy")
                .description("Achieved mastery over telepathic powers")
                .dateAwarded(LocalDate.now())
                .awardedBy("Professor X")
                .category(SubjectCategory.ACADEMIC)
                .student(student)
                .build();

        achievement = achievementRepository.save(achievement);
    }

    /**
     * Test creating a new Achievement record via the POST /api/achievements endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void createAchievement() throws Exception {
        Achievement newAchievement = Achievement.builder()
                .title("Telekinesis Mastery")
                .description("Achieved mastery over telekinetic powers")
                .dateAwarded(LocalDate.now())
                .awardedBy("Professor X")
                .category(SubjectCategory.ACADEMIC)
                .student(student)
                .build();

        mockMvc.perform(post("/api/achievements")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newAchievement)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value(newAchievement.getTitle()))
                .andExpect(jsonPath("$.description").value(newAchievement.getDescription()))
                .andExpect(jsonPath("$.awardedBy").value(newAchievement.getAwardedBy()))
                .andExpect(jsonPath("$.category").value(newAchievement.getCategory().toString()))
                .andExpect(jsonPath("$.student.id").value(newAchievement.getStudent().getId()));
    }

    /**
     * Test retrieving all Achievement records via the GET /api/achievements endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getAllAchievements() throws Exception {
        mockMvc.perform(get("/api/achievements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(achievement.getTitle()))
                .andExpect(jsonPath("$[0].description").value(achievement.getDescription()));
    }

    /**
     * Test retrieving a specific Achievement record by its ID via the GET /api/achievements/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getAchievementById() throws Exception {
        mockMvc.perform(get("/api/achievements/" + achievement.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(achievement.getTitle()))
                .andExpect(jsonPath("$.description").value(achievement.getDescription()));
    }

    /**
     * Test handling the case where an Achievement record with a non-existent ID is requested.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getAchievementByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/achievements/9999"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test updating an existing Achievement record via the PUT /api/achievements/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void updateAchievement() throws Exception {
        Achievement updatedAchievement = Achievement.builder()
                .title("Master of Telekinesis")
                .description("Mastered the art of telekinesis")
                .dateAwarded(LocalDate.now())
                .awardedBy("Professor X")
                .category(SubjectCategory.ACADEMIC)
                .student(student)
                .build();

        updatedAchievement.setId(achievement.getId());

        mockMvc.perform(put("/api/achievements/" + achievement.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedAchievement)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updatedAchievement.getTitle()))
                .andExpect(jsonPath("$.description").value(updatedAchievement.getDescription()));
    }

    /**
     * Test deleting an existing Achievement record via the DELETE /api/achievements/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void deleteAchievement() throws Exception {
        mockMvc.perform(delete("/api/achievements/" + achievement.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/achievements/" + achievement.getId()))
                .andExpect(status().isNotFound());
    }
}

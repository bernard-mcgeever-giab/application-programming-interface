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
import com.give_it_a_bash.application_programming_interface.repositories.SchoolDataRepository;
import com.give_it_a_bash.application_programming_interface.repositories.SubjectRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the Subject API endpoints.
 * This class tests CRUD operations for the Subject entity, including:
 * <ul>
 *     <li>Creating a new Subject record</li>
 *     <li>Retrieving all Subject records</li>
 *     <li>Retrieving a specific Subject record by ID</li>
 *     <li>Updating an existing Subject record</li>
 *     <li>Deleting a Subject record</li>
 * </ul>
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:subject_it;DB_CLOSE_DELAY=-1")
class SubjectIT {

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests

    @Autowired
    private SubjectRepository subjectRepository; // Repository for interacting with the database

    @Autowired
    private SchoolDataRepository schoolDataRepository; // Repository for interacting with the database

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    private Subject subject; // A class level test instance of Subject

    private SchoolData schoolData; // A class level test instance of SchoolData


    /**
     * Set up the test environment before each test.
     * This method deletes all records in the database and inserts a sample Subject record.
     */
    @BeforeEach
    void setUp() {
        subjectRepository.deleteAll();
        schoolDataRepository.deleteAll();

        schoolData = SchoolData.builder()
                .schoolName("Xavier Institute for Higher Learning")
                .location("1407 Graymalkin Lane, Salem Center, NY")
                .motto("Mutatis Mutandis")
                .yearEstablished(1963)
                .affiliation("Mutant Education and Research")
                .contactInfo("+1-555-XAVIER")
                .isActive(true)
                .build();

        subject = Subject.builder()
                .schoolData(schoolData)
                .name("Mutant Studies")
                .build();

        schoolData = schoolDataRepository.save(schoolData);
        subject = subjectRepository.save(subject);
    }

    /**
     * Test creating a new Subject record via the POST /api/subjects endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void createSubject() throws Exception {
        Subject newSubject = Subject.builder()
                .schoolData(schoolData)
                .name("Philosophy and Ethics")
                .build();

        mockMvc.perform(post("/api/subjects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSubject)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(newSubject.getName()));
    }

    /**
     * Test retrieving all Subject records via the GET /api/subjects endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getAllSubjects() throws Exception {
        mockMvc.perform(get("/api/subjects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(subject.getName()));
    }

    /**
     * Test retrieving a specific Subject record by its ID via the GET /api/subjects/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getSubjectById() throws Exception {
        mockMvc.perform(get("/api/subjects/" + subject.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(subject.getName()));
    }

    /**
     * Test handling the case where a Subjects record with a non-existent ID is requested.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getSubjectByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/subjects/9999"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test updating an existing Subject record via the PUT /api/subjects/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void updateSubject() throws Exception {
        Subject updatedSubject = Subject.builder()
                .schoolData(schoolData)
                .name("Mutant Studies I")
                .build();

        updatedSubject.setId(subject.getId());

        mockMvc.perform(put("/api/subjects/" + subject.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedSubject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedSubject.getName()));
    }

    /**
     * Test deleting an existing Subject record via the DELETE /api/subjects/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void deleteSubject() throws Exception {
        mockMvc.perform(delete("/api/subjects/" + subject.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/subjects/" + subject.getId()))
                .andExpect(status().isNotFound());
    }
}

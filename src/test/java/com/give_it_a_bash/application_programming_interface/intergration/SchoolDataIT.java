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
import com.give_it_a_bash.application_programming_interface.entities.SchoolData;
import com.give_it_a_bash.application_programming_interface.repositories.SchoolDataRepository;
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
 * Integration tests for the SchoolData API endpoints.
 * This class tests CRUD operations for the SchoolData entity, including:
 * <ul>
 *     <li>Creating a new SchoolData record</li>
 *     <li>Retrieving all SchoolData records</li>
 *     <li>Retrieving a single SchoolData record by ID</li>
 *     <li>Updating an existing SchoolData record</li>
 *     <li>Deleting a SchoolData record</li>
 * </ul>
 * <p>
 * This class uses the Spring Boot Test framework along with MockMvc to perform HTTP requests and validate responses.
 * </p>
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:school_data_it;DB_CLOSE_DELAY=-1")
class SchoolDataIT {

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests

    @Autowired
    private SchoolDataRepository schoolDataRepository; // Repository for interacting with the database

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    private SchoolData schoolData; // A class level test instance of SchoolData

    /**
     * Set up the test environment before each test.
     * This method deletes all records in the database and inserts a sample SchoolData record.
     */
    @BeforeEach
    void setUp() {
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

        schoolData = schoolDataRepository.save(schoolData);
    }

    /**
     * Test creating a new SchoolData record via the POST /api/schooldata endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void createSchoolData() throws Exception {
        SchoolData newSchoolData = SchoolData.builder()
                .schoolName("Brotherhood of Evil Mutants")
                .location("Asteroid M, Outer Space")
                .motto("Peace through Power")
                .yearEstablished(1965)
                .affiliation("Brotherhood of Evil Mutants")
                .contactInfo("+1-555-BROTHERHOOD")
                .isActive(true)
                .build();

        mockMvc.perform(post("/api/schooldata")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newSchoolData)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.schoolName").value(newSchoolData.getSchoolName()))
                .andExpect(jsonPath("$.location").value(newSchoolData.getLocation()))
                .andExpect(jsonPath("$.motto").value(newSchoolData.getMotto()))
                .andExpect(jsonPath("$.yearEstablished").value(newSchoolData.getYearEstablished()))
                .andExpect(jsonPath("$.affiliation").value(newSchoolData.getAffiliation()))
                .andExpect(jsonPath("$.contactInfo").value(newSchoolData.getContactInfo()))
                .andExpect(jsonPath("$.active").value(newSchoolData.isActive()));
    }

    /**
     * Test retrieving all SchoolData records via the GET /api/schooldata endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getAllSchoolData() throws Exception {
        mockMvc.perform(get("/api/schooldata"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].schoolName").value(schoolData.getSchoolName()))
                .andExpect(jsonPath("$[0].location").value(schoolData.getLocation()))
                .andExpect(jsonPath("$[0].motto").value(schoolData.getMotto()))
                .andExpect(jsonPath("$[0].yearEstablished").value(schoolData.getYearEstablished()))
                .andExpect(jsonPath("$[0].affiliation").value(schoolData.getAffiliation()))
                .andExpect(jsonPath("$[0].contactInfo").value(schoolData.getContactInfo()))
                .andExpect(jsonPath("$[0].active").value(schoolData.isActive()));
    }

    /**
     * Test retrieving a specific SchoolData record by its ID via the GET /api/schooldata/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getSchoolDataById() throws Exception {
        mockMvc.perform(get("/api/schooldata/" + schoolData.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolName").value(schoolData.getSchoolName()))
                .andExpect(jsonPath("$.location").value(schoolData.getLocation()))
                .andExpect(jsonPath("$.motto").value(schoolData.getMotto()))
                .andExpect(jsonPath("$.yearEstablished").value(schoolData.getYearEstablished()))
                .andExpect(jsonPath("$.affiliation").value(schoolData.getAffiliation()))
                .andExpect(jsonPath("$.contactInfo").value(schoolData.getContactInfo()))
                .andExpect(jsonPath("$.active").value(schoolData.isActive()));
    }

    /**
     * Test handling the case where a SchoolData record with a non-existent ID is requested.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getSchoolDataByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/schooldata/9999"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test updating an existing SchoolData record via the PUT /api/schooldata/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void updateSchoolData() throws Exception {
        SchoolData modifiedSchoolData = SchoolData.builder()
                .schoolName("Xavier Institute for Higher Learning")
                .location("1407 Graymalkin Lane, Salem Center, NY")
                .motto("Mutatis Mutandis")
                .yearEstablished(1963)
                .affiliation("Mutant Education and Research")
                .contactInfo("+1-555-XAVIER")
                .isActive(false)
                .build();
        modifiedSchoolData.setId(schoolData.getId());

        mockMvc.perform(put("/api/schooldata/" + schoolData.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(modifiedSchoolData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.active").value(modifiedSchoolData.isActive()));
    }


    /**
     * Test deleting an existing SchoolData record via the DELETE /api/schooldata/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void deleteSchoolData() throws Exception {
        mockMvc.perform(delete("/api/schooldata/" + schoolData.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/schooldata/" + schoolData.getId()))
                .andExpect(status().isNotFound());
    }
}

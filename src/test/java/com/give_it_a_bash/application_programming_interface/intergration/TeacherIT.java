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
import com.give_it_a_bash.application_programming_interface.repositories.TeacherRepository;
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

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the Teacher API endpoints.
 * This class tests CRUD operations for the Teacher entity, including:
 * <ul>
 *     <li>Creating a new Teacher record</li>
 *     <li>Retrieving all Teacher records</li>
 *     <li>Retrieving a specific Teacher record by ID</li>
 *     <li>Updating an existing Teacher record</li>
 *     <li>Deleting a Teacher record</li>
 * </ul>
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:teacher_it;DB_CLOSE_DELAY=-1")
class TeacherIT {

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests

    @Autowired
    private TeacherRepository teacherRepository; // Repository for interacting with the database

    @Autowired
    private SchoolDataRepository schoolDataRepository; // Repository for interacting with the database

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    private Teacher teacher; // A class level test instance of Teacher

    private SchoolData schoolData; // A class level test instance of SchoolData

    private Power power; // A class level test instance of Power

    /**
     * Set up the test environment before each test.
     * This method deletes all records in the database and inserts a sample Teacher record.
     */
    @BeforeEach
    void setUp() {
        teacherRepository.deleteAll();
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

        power = Power.builder()
                .name("Telepathy")
                .powerLevel(10)
                .description("Ability to read and control minds")
                .category("Psychic")
                .isActive(true)
                .controlLevel(10)
                .originSource(PowerSource.GENETIC_MUTATION)
                .build();

        teacher = Teacher.builder()
                .schoolData(schoolData)
                .firstName("Charles")
                .lastName("Xavier")
                .alias("Professor X")
                .power(power)
                .missionHistory(Collections.singletonList("The Cerebro Chronicles"))
                .isActive(true)
                .email("charles.xavier@xavier.edu")
                .phoneNumber("1234567890")
                .address("1407 Graymalkin Lane")
                .qualifications("PhD in Genetics")
                .yearsOfExperience(20)
                .department("Psychic Studies")
                .build();

        schoolData = schoolDataRepository.save(schoolData);
        teacher = teacherRepository.save(teacher);
    }

    /**
     * Test creating a new Teacher record via the POST /api/teacher endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void createTeacher() throws Exception {
        Power power = Power.builder()
                .name("Telepathy")
                .powerLevel(7)
                .description("Ability to read and control minds")
                .category("Psychic")
                .isActive(true)
                .controlLevel(5)
                .originSource(PowerSource.GENETIC_MUTATION)
                .build();

        Teacher newTeacher = Teacher.builder()
                .schoolData(schoolData)
                .firstName("Jean")
                .lastName("Grey")
                .alias("Phoenix")
                .power(power)
                .missionHistory(Collections.singletonList("Dark Phoenix Saga"))
                .isActive(true)
                .email("jean.grey@xavier.edu")
                .phoneNumber("0987654321")
                .address("1407 Graymalkin Lane")
                .qualifications("MSc in Psychology")
                .yearsOfExperience(10)
                .department("Telepathic Studies")
                .build();

        mockMvc.perform(post("/api/teachers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTeacher)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.schoolData.id").value(newTeacher.getSchoolData().getId()))
                .andExpect(jsonPath("$.firstName").value(newTeacher.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(newTeacher.getLastName()))
                .andExpect(jsonPath("$.alias").value(newTeacher.getAlias()))
                .andExpect(jsonPath("$.power.powerName").value(newTeacher.getPower().getName()))
                .andExpect(jsonPath("$.power.powerLevel").value(newTeacher.getPower().getPowerLevel()))
                .andExpect(jsonPath("$.power.powerDescription").value(newTeacher.getPower().getDescription()))
                .andExpect(jsonPath("$.power.powerCategory").value(newTeacher.getPower().getCategory()))
                .andExpect(jsonPath("$.power.isPowerActive").value(newTeacher.getPower().isActive()))
                .andExpect(jsonPath("$.power.controlLevel").value(newTeacher.getPower().getControlLevel()))
                .andExpect(jsonPath("$.power.originSource").value(newTeacher.getPower().getOriginSource().name()))
                .andExpect(jsonPath("$.missionHistory[0]").value(newTeacher.getMissionHistory().get(0)))
                .andExpect(jsonPath("$.isActive").value(newTeacher.getIsActive()))
                .andExpect(jsonPath("$.email").value(newTeacher.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(newTeacher.getPhoneNumber()))
                .andExpect(jsonPath("$.address").value(newTeacher.getAddress()))
                .andExpect(jsonPath("$.qualifications").value(newTeacher.getQualifications()))
                .andExpect(jsonPath("$.yearsOfExperience").value(newTeacher.getYearsOfExperience()))
                .andExpect(jsonPath("$.department").value(newTeacher.getDepartment()));
    }

    /**
     * Test retrieving all Teacher records via the GET /api/teacher endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getAllTeachers() throws Exception {
        mockMvc.perform(get("/api/teachers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].schoolData.id").value(1))
                .andExpect(jsonPath("$[0].firstName").value(teacher.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(teacher.getLastName()))
                .andExpect(jsonPath("$[0].alias").value(teacher.getAlias()))
                .andExpect(jsonPath("$[0].power.powerName").value(teacher.getPower().getName()))
                .andExpect(jsonPath("$[0].power.powerLevel").value(teacher.getPower().getPowerLevel()))
                .andExpect(jsonPath("$[0].power.powerDescription").value(teacher.getPower().getDescription()))
                .andExpect(jsonPath("$[0].power.powerCategory").value(teacher.getPower().getCategory()))
                .andExpect(jsonPath("$[0].power.isPowerActive").value(teacher.getPower().isActive()))
                .andExpect(jsonPath("$[0].power.controlLevel").value(teacher.getPower().getControlLevel()))
                .andExpect(jsonPath("$[0].power.originSource").value(teacher.getPower().getOriginSource().name()))
                .andExpect(jsonPath("$[0].missionHistory[0]").value(teacher.getMissionHistory().get(0)))
                .andExpect(jsonPath("$[0].isActive").value(teacher.getIsActive()))
                .andExpect(jsonPath("$[0].email").value(teacher.getEmail()))
                .andExpect(jsonPath("$[0].phoneNumber").value(teacher.getPhoneNumber()))
                .andExpect(jsonPath("$[0].address").value(teacher.getAddress()))
                .andExpect(jsonPath("$[0].qualifications").value(teacher.getQualifications()))
                .andExpect(jsonPath("$[0].yearsOfExperience").value(teacher.getYearsOfExperience()))
                .andExpect(jsonPath("$[0].department").value(teacher.getDepartment()));
    }

    /**
     * Test retrieving a specific Teacher record by its ID via the GET /api/teacher/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getTeacherById() throws Exception {
        mockMvc.perform(get("/api/teachers/" + teacher.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(teacher.getId()))
                .andExpect(jsonPath("$.schoolData.id").value(teacher.getSchoolData().getId()))
                .andExpect(jsonPath("$.firstName").value(teacher.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(teacher.getLastName()))
                .andExpect(jsonPath("$.alias").value(teacher.getAlias()))
                .andExpect(jsonPath("$.power.powerName").value(teacher.getPower().getName()))
                .andExpect(jsonPath("$.power.powerLevel").value(teacher.getPower().getPowerLevel()))
                .andExpect(jsonPath("$.power.powerDescription").value(teacher.getPower().getDescription()))
                .andExpect(jsonPath("$.power.powerCategory").value(teacher.getPower().getCategory()))
                .andExpect(jsonPath("$.power.isPowerActive").value(teacher.getPower().isActive()))
                .andExpect(jsonPath("$.power.controlLevel").value(teacher.getPower().getControlLevel()))
                .andExpect(jsonPath("$.power.originSource").value(teacher.getPower().getOriginSource().name()))
                .andExpect(jsonPath("$.missionHistory[0]").value(teacher.getMissionHistory().get(0)))
                .andExpect(jsonPath("$.isActive").value(teacher.getIsActive()))
                .andExpect(jsonPath("$.email").value(teacher.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(teacher.getPhoneNumber()))
                .andExpect(jsonPath("$.address").value(teacher.getAddress()))
                .andExpect(jsonPath("$.qualifications").value(teacher.getQualifications()))
                .andExpect(jsonPath("$.yearsOfExperience").value(teacher.getYearsOfExperience()))
                .andExpect(jsonPath("$.department").value(teacher.getDepartment()));
    }

    /**
     * Test handling the case where a Teacher record with a non-existent ID is requested.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getTeacherByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/teachers/9999"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test updating an existing Teacher record via the PUT /api/teacher/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void updateTeacher() throws Exception {

        Teacher updatedTeacher = Teacher.builder()
                .schoolData(schoolData)
                .firstName("Charles")
                .lastName("Xavier")
                .alias("Professor X")
                .power(power)
                .missionHistory(Collections.singletonList("The Cerebro Chronicles"))
                .isActive(false)
                .email("charles.xavier@xavier.edu")
                .phoneNumber("1234567890")
                .address("1407 Graymalkin Lane")
                .qualifications("PhD in Genetics")
                .yearsOfExperience(20)
                .department("Psychic Studies")
                .build();

        updatedTeacher.setId(teacher.getId());

        mockMvc.perform(put("/api/teachers/" + teacher.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTeacher)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isActive").value(updatedTeacher.getIsActive()));
    }

    /**
     * Test deleting an existing Teacher record via the DELETE /api/teacher/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void deleteTeacher() throws Exception {
        mockMvc.perform(delete("/api/teachers/" + teacher.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/teachers/" + teacher.getId()))
                .andExpect(status().isNotFound());
    }
}

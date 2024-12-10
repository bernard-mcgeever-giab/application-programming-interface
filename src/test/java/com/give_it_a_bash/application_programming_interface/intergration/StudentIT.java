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

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the Student API endpoints.
 * This class tests CRUD operations for the Student entity, including:
 * <ul>
 *     <li>Creating a new Student record</li>
 *     <li>Retrieving all Student records</li>
 *     <li>Retrieving a specific Student record by ID</li>
 *     <li>Updating an existing Student record</li>
 *     <li>Deleting a Student record</li>
 * </ul>
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:student_it;DB_CLOSE_DELAY=-1")
class StudentIT {

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests

    @Autowired
    private StudentRepository studentRepository; // Repository for interacting with the database

    @Autowired
    private SchoolDataRepository schoolDataRepository; // Repository for interacting with the database

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    private Student student; // A class level test instance of Student

    private SchoolData schoolData; // A class level test instance of SchoolData

    private Power power; // A class level test instance of Power

    /**
     * Set up the test environment before each test.
     * This method deletes all records in the database and inserts a sample Student record.
     */
    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
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
                .name("Cryokinesis")
                .powerLevel(5)
                .description("Ability to generate and control ice and cold temperatures")
                .category("Elemental")
                .isActive(true)
                .controlLevel(5)
                .originSource(PowerSource.GENETIC_MUTATION)
                .build();

        student = Student.builder()
                .schoolData(schoolData)
                .firstName("Bobby")
                .lastName("Drake")
                .alias("Iceman")
                .power(power)
                .missionHistory(Collections.singletonList("The Battle of the Triskelion"))
                .isActive(true)
                .guardianFirstName("William")
                .guardianLastName("Drake")
                .guardianContactNumber("+1-555-ICEMAN")
                .guardianEmail("william.drake@example.com")
                .contactNumber("+1-555-ICE1234")
                .email("bobby.drake@example.com")
                .status(Status.ACTIVE)
                .build();

        schoolData = schoolDataRepository.save(schoolData);
        student = studentRepository.save(student);
    }

    /**
     * Test creating a new Student record via the POST /api/teacher endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void createStudent() throws Exception {
        Power power = Power.builder()
                .name("Pyrokinesis")
                .powerLevel(5)
                .description("Ability to generate and control fire and heat")
                .category("Elemental")
                .isActive(true)
                .controlLevel(5)
                .originSource(PowerSource.GENETIC_MUTATION)
                .build();

        Student newStudent = Student.builder()
                .schoolData(schoolData)
                .firstName("Angelica")
                .lastName("Jones")
                .alias("Firestar")
                .power(power)
                .missionHistory(Collections.singletonList("The Mutant Massacre"))
                .isActive(true)
                .guardianFirstName("Anna")
                .guardianLastName("Jones")
                .guardianContactNumber("+1-555-FIRESTAR")
                .guardianEmail("anna.jones@example.com")
                .contactNumber("+1-555-FIRE1234")
                .email("angelica.jones@example.com")
                .status(Status.ACTIVE)
                .build();

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.schoolData.id").value(newStudent.getSchoolData().getId()))
                .andExpect(jsonPath("$.firstName").value(newStudent.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(newStudent.getLastName()))
                .andExpect(jsonPath("$.alias").value(newStudent.getAlias()))
                .andExpect(jsonPath("$.power.powerName").value(newStudent.getPower().getName()))
                .andExpect(jsonPath("$.power.powerLevel").value(newStudent.getPower().getPowerLevel()))
                .andExpect(jsonPath("$.power.powerDescription").value(newStudent.getPower().getDescription()))
                .andExpect(jsonPath("$.power.powerCategory").value(newStudent.getPower().getCategory()))
                .andExpect(jsonPath("$.power.isPowerActive").value(newStudent.getPower().isActive()))
                .andExpect(jsonPath("$.power.controlLevel").value(newStudent.getPower().getControlLevel()))
                .andExpect(jsonPath("$.power.originSource").value(newStudent.getPower().getOriginSource().name()))
                .andExpect(jsonPath("$.missionHistory[0]").value(newStudent.getMissionHistory().get(0)))
                .andExpect(jsonPath("$.isActive").value(newStudent.getIsActive()))
                .andExpect(jsonPath("$.guardianFirstName").value(newStudent.getGuardianFirstName()))
                .andExpect(jsonPath("$.guardianLastName").value(newStudent.getGuardianLastName()))
                .andExpect(jsonPath("$.guardianContactNumber").value(newStudent.getGuardianContactNumber()))
                .andExpect(jsonPath("$.guardianEmail").value(newStudent.getGuardianEmail()))
                .andExpect(jsonPath("$.contactNumber").value(newStudent.getContactNumber()))
                .andExpect(jsonPath("$.email").value(newStudent.getEmail()))
                .andExpect(jsonPath("$.status").value(newStudent.getStatus().name()));
    }

    /**
     * Test retrieving all Student records via the GET /api/student endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getAllStudents() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].schoolData.id").value(student.getSchoolData().getId()))
                .andExpect(jsonPath("$[0].firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(student.getLastName()))
                .andExpect(jsonPath("$[0].alias").value(student.getAlias()))
                .andExpect(jsonPath("$[0].power.powerName").value(student.getPower().getName()))
                .andExpect(jsonPath("$[0].power.powerLevel").value(student.getPower().getPowerLevel()))
                .andExpect(jsonPath("$[0].power.powerDescription").value(student.getPower().getDescription()))
                .andExpect(jsonPath("$[0].power.powerCategory").value(student.getPower().getCategory()))
                .andExpect(jsonPath("$[0].power.isPowerActive").value(student.getPower().isActive()))
                .andExpect(jsonPath("$[0].power.controlLevel").value(student.getPower().getControlLevel()))
                .andExpect(jsonPath("$[0].power.originSource").value(student.getPower().getOriginSource().name()))
                .andExpect(jsonPath("$[0].missionHistory[0]").value(student.getMissionHistory().get(0)))
                .andExpect(jsonPath("$[0].isActive").value(student.getIsActive()))
                .andExpect(jsonPath("$[0].guardianFirstName").value(student.getGuardianFirstName()))
                .andExpect(jsonPath("$[0].guardianLastName").value(student.getGuardianLastName()))
                .andExpect(jsonPath("$[0].guardianContactNumber").value(student.getGuardianContactNumber()))
                .andExpect(jsonPath("$[0].guardianEmail").value(student.getGuardianEmail()))
                .andExpect(jsonPath("$[0].contactNumber").value(student.getContactNumber()))
                .andExpect(jsonPath("$[0].email").value(student.getEmail()))
                .andExpect(jsonPath("$[0].status").value(student.getStatus().name()));
    }

    /**
     * Test retrieving a specific Student record by its ID via the GET /api/student/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getStudentById() throws Exception {
        mockMvc.perform(get("/api/students/" + student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.schoolData.id").value(student.getSchoolData().getId()))
                .andExpect(jsonPath("$.firstName").value(student.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(student.getLastName()))
                .andExpect(jsonPath("$.alias").value(student.getAlias()))
                .andExpect(jsonPath("$.power.powerName").value(student.getPower().getName()))
                .andExpect(jsonPath("$.power.powerLevel").value(student.getPower().getPowerLevel()))
                .andExpect(jsonPath("$.power.powerDescription").value(student.getPower().getDescription()))
                .andExpect(jsonPath("$.power.powerCategory").value(student.getPower().getCategory()))
                .andExpect(jsonPath("$.power.isPowerActive").value(student.getPower().isActive()))
                .andExpect(jsonPath("$.power.controlLevel").value(student.getPower().getControlLevel()))
                .andExpect(jsonPath("$.power.originSource").value(student.getPower().getOriginSource().name()))
                .andExpect(jsonPath("$.missionHistory[0]").value(student.getMissionHistory().get(0)))
                .andExpect(jsonPath("$.isActive").value(student.getIsActive()))
                .andExpect(jsonPath("$.guardianFirstName").value(student.getGuardianFirstName()))
                .andExpect(jsonPath("$.guardianLastName").value(student.getGuardianLastName()))
                .andExpect(jsonPath("$.guardianContactNumber").value(student.getGuardianContactNumber()))
                .andExpect(jsonPath("$.guardianEmail").value(student.getGuardianEmail()))
                .andExpect(jsonPath("$.contactNumber").value(student.getContactNumber()))
                .andExpect(jsonPath("$.email").value(student.getEmail()))
                .andExpect(jsonPath("$.status").value(student.getStatus().name()));
    }

    /**
     * Test handling the case where a Student record with a non-existent ID is requested.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getTeacherByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/students/9999"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test updating an existing Student record via the PUT /api/student/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void updateStudent() throws Exception {

        Student updatedStudent = Student.builder()
                .schoolData(schoolData)
                .firstName("Bobby")
                .lastName("Drake")
                .alias("Iceman")
                .power(power)
                .missionHistory(Collections.singletonList("The Battle of the Triskelion"))
                .isActive(false)
                .guardianFirstName("William")
                .guardianLastName("Drake")
                .guardianContactNumber("+1-555-ICEMAN")
                .guardianEmail("william.drake@example.com")
                .contactNumber("+1-555-ICE1234")
                .email("bobby.drake@example.com")
                .status(Status.ACTIVE)
                .build();

        updatedStudent.setId(student.getId());

        mockMvc.perform(put("/api/students/" + student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isActive").value(updatedStudent.getIsActive()));
    }

    /**
     * Test deleting an existing Student record via the DELETE /api/student/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void deleteTeacher() throws Exception {
        mockMvc.perform(delete("/api/students/" + student.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/students/" + student.getId()))
                .andExpect(status().isNotFound());
    }
}

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
import com.give_it_a_bash.application_programming_interface.repositories.LessonRepository;
import com.give_it_a_bash.application_programming_interface.repositories.SchoolDataRepository;
import com.give_it_a_bash.application_programming_interface.repositories.TeacherRepository;
import com.give_it_a_bash.application_programming_interface.repositories.SubjectRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the Lesson API endpoints.
 * This class tests CRUD operations for the Lesson entity, including:
 * <ul>
 *     <li>Creating a new Lesson</li>
 *     <li>Retrieving all Lessons</li>
 *     <li>Retrieving a specific Lesson by ID</li>
 *     <li>Updating an existing Lesson</li>
 *     <li>Deleting a Lesson</li>
 * </ul>
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:lesson_it;DB_CLOSE_DELAY=-1")
class LessonIT {

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests

    @Autowired
    private LessonRepository lessonRepository; // Repository for interacting with the database

    @Autowired
    private SchoolDataRepository schoolDataRepository; // Repository for interacting with the database

    @Autowired
    private TeacherRepository teacherRepository; // Repository for interacting with the database

    @Autowired
    private SubjectRepository subjectRepository; // Repository for interacting with the database

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    private Lesson lesson; // A class level test instance of Lesson

    private SchoolData schoolData; // A class level test instance of SchoolData

    private Teacher teacher; // A class level test instance of Teacher

    private Power power; // A class level test instance of Power

    private Subject subject; // A class level test instance of Subject

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); // Formatter to aid DateTime assertions in tests


    /**
     * Sets up the initial state for the test by clearing the {@link SchoolDataRepository}
     * and creating a new {@link SchoolData} entity for use in the test cases.
     */
    @BeforeAll
    void initializeSchoolDataTestData() {
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
     * Set up the test environment before each test.
     * This method deletes all records in the database and inserts a sample Lesson record.
     */
    @BeforeEach
    void setUp() {
        lessonRepository.deleteAll();
        teacherRepository.deleteAll();

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
        teacher = teacherRepository.save(teacher);


        subject = Subject.builder()
                .schoolData(schoolData)
                .name("Mutant Studies")
                .build();
        subject = subjectRepository.save(subject);


        lesson = Lesson.builder()
                .subject(subject)
                .teacher(teacher)
                .startTime(LocalDateTime.of(2024, 12, 11, 9, 0))
                .endTime(LocalDateTime.of(2024, 12, 11, 10, 0))
                .build();
        lesson = lessonRepository.save(lesson);
    }

    /**
     * Test creating a new Lesson record via the POST /api/lessons endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void createLesson() throws Exception {
        Lesson newLesson = Lesson.builder()
                .subject(subject)
                .teacher(teacher)
                .startTime(LocalDateTime.of(2024, 12, 18, 9, 0))
                .endTime(LocalDateTime.of(2024, 12, 18, 10, 0))
                .build();

        mockMvc.perform(post("/api/lessons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newLesson)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subject.id").value(newLesson.getSubject().getId()))
                .andExpect(jsonPath("$.teacher.id").value(newLesson.getTeacher().getId()))
                .andExpect(jsonPath("$.startTime").value(newLesson.getStartTime().format(formatter)))
                .andExpect(jsonPath("$.endTime").value(newLesson.getEndTime().format(formatter)));
    }

    /**
     * Test retrieving all Lesson records via the GET /api/lessons endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getAllLessons() throws Exception {
        mockMvc.perform(get("/api/lessons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].subject.id").value(lesson.getSubject().getId()))
                .andExpect(jsonPath("$[0].teacher.id").value(lesson.getTeacher().getId()))
                .andExpect(jsonPath("$[0].startTime").value(lesson.getStartTime().format(formatter)))
                .andExpect(jsonPath("$[0].endTime").value(lesson.getEndTime().format(formatter)));
    }

    /**
     * Test retrieving a specific Lesson record by its ID via the GET /api/lessons/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getLessonById() throws Exception {
        mockMvc.perform(get("/api/lessons/" + lesson.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.subject.id").value(lesson.getSubject().getId()))
                .andExpect(jsonPath("$.teacher.id").value(lesson.getTeacher().getId()))
                .andExpect(jsonPath("$.startTime").value(lesson.getStartTime().format(formatter)))
                .andExpect(jsonPath("$.endTime").value(lesson.getEndTime().format(formatter)));
    }

    /**
     * Test handling the case where a Lesson record with a non-existent ID is requested.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getLessonByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/lessons/9999"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test updating an existing Lesson record via the PUT /api/lessons/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void updateLesson() throws Exception {

        Lesson updatedLesson = Lesson.builder()
                .subject(subject)
                .teacher(teacher)
                .startTime(LocalDateTime.of(2024, 12, 11, 9, 0))
                .endTime(LocalDateTime.of(2024, 12, 11, 10, 30))
                .build();

        updatedLesson.setId(lesson.getId());

        mockMvc.perform(put("/api/lessons/" + lesson.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedLesson)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.endTime").value(updatedLesson.getEndTime().format(formatter)));
    }

    /**
     * Test deleting an existing Lesson record via the DELETE /api/lessons/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void deleteLesson() throws Exception {
        mockMvc.perform(delete("/api/lessons/" + lesson.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/lessons/" + lesson.getId()))
                .andExpect(status().isNotFound());
    }
}

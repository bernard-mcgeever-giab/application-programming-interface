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

import com.give_it_a_bash.application_programming_interface.entities.Lesson;
import com.give_it_a_bash.application_programming_interface.services.LessonService;
import com.give_it_a_bash.application_programming_interface.testData.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the LessonController class.
 * This class tests the RESTful endpoints for creating, retrieving, updating, and deleting Lesson entities.
 */
class LessonControllerTest {

    @Mock
    private LessonService lessonService;

    @InjectMocks
    private LessonController lessonController;

    private Lesson lesson;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lesson = TestDataHelper.getLESSON();
    }

    /**
     * Tests the createLesson() method.
     * Verifies that a new Lesson is created successfully and returns the correct HTTP status.
     */
    @Test
    void createLesson_ReturnsCreatedLesson() {
        when(lessonService.createLesson(any(Lesson.class))).thenReturn(lesson);

        ResponseEntity<Lesson> response = lessonController.createLesson(lesson);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(lesson, response.getBody());
        verify(lessonService, times(1)).createLesson(lesson);
    }

    /**
     * Tests the getAllLessons() method.
     * Verifies that all Lessons are retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getAllLessons_ReturnsListOfLessons() {
        List<Lesson> lessons = Collections.singletonList(lesson);
        when(lessonService.getAllLessons()).thenReturn(lessons);

        ResponseEntity<List<Lesson>> response = lessonController.getAllLessons();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lessons, response.getBody());
        verify(lessonService, times(1)).getAllLessons();
    }

    /**
     * Tests the getLessonById() method for an existing ID.
     * Verifies that the Lesson is retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getLessonById_ExistingId_ReturnsLesson() {
        when(lessonService.getLessonById(1L)).thenReturn(Optional.of(lesson));

        ResponseEntity<Lesson> response = lessonController.getLessonById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lesson, response.getBody());
        verify(lessonService, times(1)).getLessonById(1L);
    }

    /**
     * Tests the getLessonById() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Lesson is not found.
     */
    @Test
    void getLessonById_NonExistingId_ReturnsNotFound() {
        when(lessonService.getLessonById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Lesson> response = lessonController.getLessonById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(lessonService, times(1)).getLessonById(1L);
    }

    /**
     * Tests the updateLesson() method for an existing ID.
     * Verifies that an existing Lesson is updated successfully and returns the correct HTTP status.
     */
    @Test
    void updateLesson_ExistingId_ReturnsUpdatedLesson() {
        Lesson updatedLesson = TestDataHelper.createLesson(LocalDateTime.of(2024, 12, 15, 14, 0),
                LocalDateTime.of(2024, 12, 15, 15, 30));
        when(lessonService.updateLesson(eq(1L), any(Lesson.class))).thenReturn(updatedLesson);

        ResponseEntity<Lesson> response = lessonController.updateLesson(1L, lesson);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedLesson, response.getBody());
        verify(lessonService, times(1)).updateLesson(1L, lesson);
    }

    /**
     * Tests the updateLesson() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Lesson is not found.
     */
    @Test
    void updateLesson_NonExistingId_ReturnsNotFound() {
        when(lessonService.updateLesson(eq(1L), any(Lesson.class)))
                .thenThrow(new RuntimeException("Lesson not found with id 1")); // Consider a specific exception type

        ResponseEntity<Lesson> response = lessonController.updateLesson(1L, lesson);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(lessonService, times(1)).updateLesson(eq(1L), any(Lesson.class));
    }

    /**
     * Tests the deleteLesson() method for an existing ID.
     * Verifies that an existing Lesson is deleted successfully and returns the correct HTTP status.
     */
    @Test
    void deleteLesson_ExistingId_ReturnsNoContent() {
        doNothing().when(lessonService).deleteLesson(1L);

        ResponseEntity<Void> response = lessonController.deleteLesson(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(lessonService, times(1)).deleteLesson(1L);
    }

    /**
     * Tests the deleteLesson() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Lesson is not found.
     */
    @Test
    void deleteLesson_NonExistingId_ReturnsNotFound() {
        doThrow(new RuntimeException("Lesson not found")).when(lessonService).deleteLesson(1L);

        ResponseEntity<Void> response = lessonController.deleteLesson(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(lessonService, times(1)).deleteLesson(1L);
    }
}

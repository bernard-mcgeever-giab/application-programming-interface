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

import com.give_it_a_bash.application_programming_interface.entities.Teacher;
import com.give_it_a_bash.application_programming_interface.services.TeacherService;
import com.give_it_a_bash.application_programming_interface.testData.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the TeacherController class.
 * This class tests the RESTful endpoints for creating, retrieving, updating, and deleting Teacher entities.
 */
class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    private Teacher teacher;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        teacher = TestDataHelper.getTEACHER();
    }

    /**
     * Tests the createTeacher() method.
     * Verifies that a new Teacher is created successfully and returns the correct HTTP status.
     */
    @Test
    void createTeacher_ReturnsCreatedTeacher() {
        when(teacherService.createTeacher(any(Teacher.class))).thenReturn(teacher);

        ResponseEntity<Teacher> response = teacherController.createTeacher(teacher);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(teacher, response.getBody());
        verify(teacherService, times(1)).createTeacher(teacher);
    }

    /**
     * Tests the getAllTeachers() method.
     * Verifies that all Teacher entries are retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getAllTeachers_ReturnsListOfTeachers() {
        List<Teacher> teacherList = Collections.singletonList(teacher);
        when(teacherService.getAllTeachers()).thenReturn(teacherList);

        ResponseEntity<List<Teacher>> response = teacherController.getAllTeachers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacherList, response.getBody());
        verify(teacherService, times(1)).getAllTeachers();
    }

    /**
     * Tests the getTeacherById() method for an existing ID.
     * Verifies that the Teacher is retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getTeacherById_ExistingId_ReturnsTeacher() {
        when(teacherService.getTeacherById(1L)).thenReturn(Optional.of(teacher));

        ResponseEntity<Teacher> response = teacherController.getTeacherById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(teacher, response.getBody());
        verify(teacherService, times(1)).getTeacherById(1L);
    }

    /**
     * Tests the getTeacherById() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Teacher is not found.
     */
    @Test
    void getTeacherById_NonExistingId_ReturnsNotFound() {
        when(teacherService.getTeacherById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Teacher> response = teacherController.getTeacherById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(teacherService, times(1)).getTeacherById(1L);
    }

    /**
     * Tests the updateTeacher() method for an existing ID.
     * Verifies that an existing Teacher is updated successfully and returns the correct HTTP status.
     */
    @Test
    void updateTeacher_ExistingId_ReturnsUpdatedTeacher() {
        Teacher updatedTeacher = TestDataHelper.createTeacher("Erik",
                "Lehnsherr",
                "Magneto",
                "magneto@example.com",
                "+1-555-0404",
                "Genosha",
                "Master in Metallurgy",
                25,
                "Magnetic Studies",
                true,
                Collections.singletonList("Founded the Brotherhood of Mutants"));
        when(teacherService.updateTeacher(eq(1L), any(Teacher.class))).thenReturn(updatedTeacher);

        ResponseEntity<Teacher> response = teacherController.updateTeacher(1L, teacher);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedTeacher, response.getBody());
        verify(teacherService, times(1)).updateTeacher(1L, teacher);
    }

    /**
     * Tests the updateTeacher() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Teacher is not found.
     */
    @Test
    void updateTeacher_NonExistingId_ReturnsNotFound() {
        when(teacherService.updateTeacher(eq(1L), any(Teacher.class)))
                .thenThrow(new RuntimeException("Teacher not found with id 1")); // Consider a specific exception type

        ResponseEntity<Teacher> response = teacherController.updateTeacher(1L, teacher);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(teacherService, times(1)).updateTeacher(eq(1L), any(Teacher.class));
    }

    /**
     * Tests the deleteTeacher() method for an existing ID.
     * Verifies that an existing Teacher is deleted successfully and returns the correct HTTP status.
     */
    @Test
    void deleteTeacher_ExistingId_ReturnsNoContent() {
        doNothing().when(teacherService).deleteTeacher(1L);

        ResponseEntity<Void> response = teacherController.deleteTeacher(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(teacherService, times(1)).deleteTeacher(1L);
    }

    /**
     * Tests the deleteTeacher() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Teacher is not found.
     */
    @Test
    void deleteTeacher_NonExistingId_ReturnsNotFound() {
        doThrow(new RuntimeException("Teacher not found")).when(teacherService).deleteTeacher(1L);

        ResponseEntity<Void> response = teacherController.deleteTeacher(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(teacherService, times(1)).deleteTeacher(1L);
    }
}

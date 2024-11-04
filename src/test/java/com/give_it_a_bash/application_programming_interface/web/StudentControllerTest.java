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

import com.give_it_a_bash.application_programming_interface.entities.Status;
import com.give_it_a_bash.application_programming_interface.entities.Student;
import com.give_it_a_bash.application_programming_interface.services.StudentService;
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
 * Unit tests for the StudentController class.
 * This class tests the RESTful endpoints for creating, retrieving, updating, and deleting Student entities.
 */
class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = TestDataHelper.getSTUDENT();
    }

    /**
     * Tests the createStudent() method.
     * Verifies that a new Student is created successfully and returns the correct HTTP status.
     */
    @Test
    void createStudent_ReturnsCreatedStudent() {
        when(studentService.createStudent(any(Student.class))).thenReturn(student);

        ResponseEntity<Student> response = studentController.createStudent(student);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(student, response.getBody());
        verify(studentService, times(1)).createStudent(student);
    }

    /**
     * Tests the getAllStudents() method.
     * Verifies that all Student entries are retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getAllStudents_ReturnsListOfStudents() {
        List<Student> studentList = Collections.singletonList(student);
        when(studentService.getAllStudents()).thenReturn(studentList);

        ResponseEntity<List<Student>> response = studentController.getAllStudents();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(studentList, response.getBody());
        verify(studentService, times(1)).getAllStudents();
    }

    /**
     * Tests the getStudentById() method for an existing ID.
     * Verifies that the Student is retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getStudentById_ExistingId_ReturnsStudent() {
        when(studentService.getStudentById(1L)).thenReturn(Optional.of(student));

        ResponseEntity<Student> response = studentController.getStudentById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(student, response.getBody());
        verify(studentService, times(1)).getStudentById(1L);
    }

    /**
     * Tests the getStudentById() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Student is not found.
     */
    @Test
    void getStudentById_NonExistingId_ReturnsNotFound() {
        when(studentService.getStudentById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Student> response = studentController.getStudentById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(studentService, times(1)).getStudentById(1L);
    }

    /**
     * Tests the updateStudent() method for an existing ID.
     * Verifies that an existing Student is updated successfully and returns the correct HTTP status.
     */
    @Test
    void updateStudent_ExistingId_ReturnsUpdatedStudent() {
        Student updatedStudent = TestDataHelper.createStudent("Scott",
                "Summers",
                "Cyclops",
                "Chris",
                "Summers",
                "+1-555-0505",
                "chris.summers@example.com",
                "+1-555-0606",
                "scott.summers@example.com",
                true,
                Status.ACTIVE,
                Collections.singletonList("Mission X"));
        when(studentService.updateStudent(eq(1L), any(Student.class))).thenReturn(updatedStudent);

        ResponseEntity<Student> response = studentController.updateStudent(1L, student);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedStudent, response.getBody());
        verify(studentService, times(1)).updateStudent(1L, student);
    }

    /**
     * Tests the updateStudent() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Student is not found.
     */
    @Test
    void updateStudent_NonExistingId_ReturnsNotFound() {
        when(studentService.updateStudent(eq(1L), any(Student.class)))
                .thenThrow(new RuntimeException("Student not found with id 1")); // Consider a specific exception type

        ResponseEntity<Student> response = studentController.updateStudent(1L, student);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(studentService, times(1)).updateStudent(eq(1L), any(Student.class));
    }

    /**
     * Tests the deleteStudent() method for an existing ID.
     * Verifies that an existing Student is deleted successfully and returns the correct HTTP status.
     */
    @Test
    void deleteStudent_ExistingId_ReturnsNoContent() {
        doNothing().when(studentService).deleteStudent(1L);

        ResponseEntity<Void> response = studentController.deleteStudent(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(studentService, times(1)).deleteStudent(1L);
    }

    /**
     * Tests the deleteStudent() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Student is not found.
     */
    @Test
    void deleteStudent_NonExistingId_ReturnsNotFound() {
        doThrow(new RuntimeException("Student not found")).when(studentService).deleteStudent(1L);

        ResponseEntity<Void> response = studentController.deleteStudent(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(studentService, times(1)).deleteStudent(1L);
    }
}

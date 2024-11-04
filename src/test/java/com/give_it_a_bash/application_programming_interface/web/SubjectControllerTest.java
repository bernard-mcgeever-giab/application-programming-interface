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

import com.give_it_a_bash.application_programming_interface.entities.Subject;
import com.give_it_a_bash.application_programming_interface.services.SubjectService;
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
 * Unit tests for the SubjectController class.
 * This class tests the RESTful endpoints for creating, retrieving, updating, and deleting Subject entities.
 */
class SubjectControllerTest {

    @Mock
    private SubjectService subjectService;

    @InjectMocks
    private SubjectController subjectController;

    private Subject subject;

    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = TestDataHelper.getSUBJECT();
    }

    /**
     * Tests the createSubject() method.
     * Verifies that a new Subject is created successfully and returns the correct HTTP status.
     */
    @Test
    void createSubject_ReturnsCreatedSubject() {
        when(subjectService.createSubject(any(Subject.class))).thenReturn(subject);

        ResponseEntity<Subject> response = subjectController.createSubject(subject);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(subject, response.getBody());
        verify(subjectService, times(1)).createSubject(subject);
    }

    /**
     * Tests the getAllSubjects() method.
     * Verifies that all Subject entries are retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getAllSubjects_ReturnsListOfSubjects() {
        List<Subject> subjectList = Collections.singletonList(subject);
        when(subjectService.getAllSubjects()).thenReturn(subjectList);

        ResponseEntity<List<Subject>> response = subjectController.getAllSubjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subjectList, response.getBody());
        verify(subjectService, times(1)).getAllSubjects();
    }

    /**
     * Tests the getSubjectById() method for an existing ID.
     * Verifies that the Subject is retrieved successfully and returns the correct HTTP status.
     */
    @Test
    void getSubjectById_ExistingId_ReturnsSubject() {
        when(subjectService.getSubjectById(1L)).thenReturn(Optional.of(subject));

        ResponseEntity<Subject> response = subjectController.getSubjectById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(subject, response.getBody());
        verify(subjectService, times(1)).getSubjectById(1L);
    }

    /**
     * Tests the getSubjectById() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Subject is not found.
     */
    @Test
    void getSubjectById_NonExistingId_ReturnsNotFound() {
        when(subjectService.getSubjectById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Subject> response = subjectController.getSubjectById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(subjectService, times(1)).getSubjectById(1L);
    }

    /**
     * Tests the updateSubject() method for an existing ID.
     * Verifies that an existing Subject is updated successfully and returns the correct HTTP status.
     */
    @Test
    void updateSubject_ExistingId_ReturnsUpdatedSubject() {
        Subject updatedSubject = TestDataHelper.createSubject("Mutant Ethics");
        when(subjectService.updateSubject(eq(1L), any(Subject.class))).thenReturn(updatedSubject);

        ResponseEntity<Subject> response = subjectController.updateSubject(1L, subject);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedSubject, response.getBody());
        verify(subjectService, times(1)).updateSubject(1L, subject);
    }

    /**
     * Tests the updateSubject() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Subject is not found.
     */
    @Test
    void updateSubject_NonExistingId_ReturnsNotFound() {
        when(subjectService.updateSubject(eq(1L), any(Subject.class)))
                .thenThrow(new RuntimeException("Subject not found with id 1"));

        ResponseEntity<Subject> response = subjectController.updateSubject(1L, subject);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(subjectService, times(1)).updateSubject(eq(1L), any(Subject.class));
    }

    /**
     * Tests the deleteSubject() method for an existing ID.
     * Verifies that an existing Subject is deleted successfully and returns the correct HTTP status.
     */
    @Test
    void deleteSubject_ExistingId_ReturnsNoContent() {
        doNothing().when(subjectService).deleteSubject(1L);

        ResponseEntity<Void> response = subjectController.deleteSubject(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(subjectService, times(1)).deleteSubject(1L);
    }

    /**
     * Tests the deleteSubject() method for a non-existing ID.
     * Verifies that a NOT_FOUND response is returned when the Subject is not found.
     */
    @Test
    void deleteSubject_NonExistingId_ReturnsNotFound() {
        doThrow(new RuntimeException("Subject not found")).when(subjectService).deleteSubject(1L);

        ResponseEntity<Void> response = subjectController.deleteSubject(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(subjectService, times(1)).deleteSubject(1L);
    }
}

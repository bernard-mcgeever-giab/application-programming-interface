/*
 * Copyright (c) 2024 Give It A Bash
 *
 * This file is part of Give It A Bash proprietary software.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 * Proprietary and confidential.
 *
 * Created and maintained by Give It A Bash.
 */

package com.give_it_a_bash.application_programming_interface.services;

import com.give_it_a_bash.application_programming_interface.entities.Subject;
import com.give_it_a_bash.application_programming_interface.repositories.SubjectRepository;
import com.give_it_a_bash.application_programming_interface.testData.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link SubjectService} class.
 *
 * These tests cover CRUD operations provided by SubjectService,
 * verifying correct interaction with the {@link SubjectRepository}.
 */
class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    private Subject subject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        subject = TestDataHelper.getSUBJECT();
    }

    /**
     * Tests the creation of a new {@link Subject} entry.
     * Verifies that the subject is saved in the repository.
     */
    @Test
    void testCreateSubject() {
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        Subject createdSubject = subjectService.createSubject(subject);

        assertThat(createdSubject).isEqualTo(subject);
        verify(subjectRepository, times(1)).save(subject);
    }

    /**
     * Tests retrieval of all {@link Subject} entries.
     * Verifies that the service retrieves the expected list of subjects.
     */
    @Test
    void testGetAllSubjects() {
        List<Subject> subjectList = Collections.singletonList(subject);
        when(subjectRepository.findAll()).thenReturn(subjectList);

        List<Subject> result = subjectService.getAllSubjects();

        assertThat(result).containsExactlyElementsOf(subjectList);
        verify(subjectRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of {@link Subject} by its ID.
     * Verifies that the correct subject is returned when found.
     */
    @Test
    void testGetSubjectById() {
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));

        Optional<Subject> result = subjectService.getSubjectById(1L);

        assertThat(result).isPresent().contains(subject);
        verify(subjectRepository, times(1)).findById(1L);
    }

    /**
     * Tests retrieval of {@link Subject} by a non-existent ID.
     * Verifies that an empty Optional is returned when the ID is not found.
     */
    @Test
    void testGetSubjectById_NotFound() {
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Subject> result = subjectService.getSubjectById(1L);

        assertThat(result).isEmpty();
        verify(subjectRepository, times(1)).findById(1L);
    }

    /**
     * Tests updating an existing {@link Subject} entry.
     * Verifies that the subject is updated with the new details provided.
     */
    @Test
    void testUpdateSubject() {
        Subject updatedDetails = TestDataHelper.createSubject("Mutant Ethics");

        when(subjectRepository.findById(anyLong())).thenReturn(Optional.of(subject));
        when(subjectRepository.save(any(Subject.class))).thenReturn(updatedDetails);

        Subject updatedSubject = subjectService.updateSubject(1L, updatedDetails);

        assertThat(updatedSubject.getName()).isEqualTo(updatedDetails.getName());
        verify(subjectRepository, times(1)).findById(1L);
        verify(subjectRepository, times(1)).save(subject);
    }

    /**
     * Tests updating a {@link Subject} with a non-existent ID.
     * Verifies that a {@link RuntimeException} is thrown when the subject is not found.
     */
    @Test
    void testUpdateSubject_NotFound() {
        when(subjectRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> subjectService.updateSubject(1L, subject))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Subject not found with id 1");
        verify(subjectRepository, times(1)).findById(1L);
        verify(subjectRepository, never()).save(any(Subject.class));
    }

    /**
     * Tests the deletion of a {@link Subject} entry by its ID.
     * Verifies that the subject is deleted from the repository.
     */
    @Test
    void testDeleteSubject() {
        doNothing().when(subjectRepository).deleteById(anyLong());

        subjectService.deleteSubject(1L);

        verify(subjectRepository, times(1)).deleteById(1L);
    }
}

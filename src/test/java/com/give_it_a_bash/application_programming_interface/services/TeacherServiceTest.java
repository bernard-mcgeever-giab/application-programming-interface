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

import com.give_it_a_bash.application_programming_interface.entities.Teacher;
import com.give_it_a_bash.application_programming_interface.repositories.TeacherRepository;
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
 * Unit tests for the {@link TeacherService} class.
 *
 * These tests cover CRUD operations provided by TeacherService,
 * verifying correct interaction with the {@link TeacherRepository}.
 */
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    private Teacher teacher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        teacher = TestDataHelper.getTEACHER();
    }

    /**
     * Tests the creation of a new {@link Teacher} entry.
     * Verifies that the teacher is saved in the repository.
     */
    @Test
    void testCreateTeacher() {
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        Teacher createdTeacher = teacherService.createTeacher(teacher);

        assertThat(createdTeacher).isEqualTo(teacher);
        verify(teacherRepository, times(1)).save(teacher);
    }

    /**
     * Tests retrieval of all {@link Teacher} entries.
     * Verifies that the service retrieves the expected list of teachers.
     */
    @Test
    void testGetAllTeachers() {
        List<Teacher> teacherList = Collections.singletonList(teacher);
        when(teacherRepository.findAll()).thenReturn(teacherList);

        List<Teacher> result = teacherService.getAllTeachers();

        assertThat(result).containsExactlyElementsOf(teacherList);
        verify(teacherRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of {@link Teacher} by its ID.
     * Verifies that the correct teacher is returned when found.
     */
    @Test
    void testGetTeacherById() {
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacher));

        Optional<Teacher> result = teacherService.getTeacherById(1L);

        assertThat(result).isPresent().contains(teacher);
        verify(teacherRepository, times(1)).findById(1L);
    }

    /**
     * Tests retrieval of {@link Teacher} by a non-existent ID.
     * Verifies that an empty Optional is returned when the ID is not found.
     */
    @Test
    void testGetTeacherById_NotFound() {
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Teacher> result = teacherService.getTeacherById(1L);

        assertThat(result).isEmpty();
        verify(teacherRepository, times(1)).findById(1L);
    }

    /**
     * Tests updating an existing {@link Teacher} entry.
     * Verifies that the teacher is updated with the new details provided.
     */
    @Test
    void testUpdateTeacher() {
        Teacher updatedDetails = TestDataHelper.createTeacher("Erik",
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
        updatedDetails.setEmail("new_teacher@example.com");

        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(updatedDetails);

        Teacher updatedTeacher = teacherService.updateTeacher(1L, updatedDetails);

        assertThat(updatedTeacher.getEmail()).isEqualTo(updatedDetails.getEmail());
        verify(teacherRepository, times(1)).findById(1L);
        verify(teacherRepository, times(1)).save(teacher);
    }

    /**
     * Tests updating a {@link Teacher} with a non-existent ID.
     * Verifies that a {@link RuntimeException} is thrown when the teacher is not found.
     */
    @Test
    void testUpdateTeacher_NotFound() {
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teacherService.updateTeacher(1L, teacher))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Teacher not found with id 1");
        verify(teacherRepository, times(1)).findById(1L);
        verify(teacherRepository, never()).save(any(Teacher.class));
    }

    /**
     * Tests the deletion of a {@link Teacher} entry by its ID.
     * Verifies that the teacher is deleted from the repository.
     */
    @Test
    void testDeleteTeacher() {
        doNothing().when(teacherRepository).deleteById(anyLong());

        teacherService.deleteTeacher(1L);

        verify(teacherRepository, times(1)).deleteById(1L);
    }
}

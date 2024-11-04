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

import com.give_it_a_bash.application_programming_interface.entities.Status;
import com.give_it_a_bash.application_programming_interface.entities.Student;
import com.give_it_a_bash.application_programming_interface.repositories.StudentRepository;
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
 * Unit tests for the {@link StudentService} class.
 *
 * These tests cover CRUD operations provided by StudentService,
 * ensuring correct behavior when interacting with {@link StudentRepository}.
 */
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = TestDataHelper.getSTUDENT();
    }

    /**
     * Tests the creation of a new {@link Student} entry.
     * Verifies that the student is saved in the repository.
     */
    @Test
    void testCreateStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        Student createdStudent = studentService.createStudent(student);

        assertThat(createdStudent).isEqualTo(student);
        verify(studentRepository, times(1)).save(student);
    }

    /**
     * Tests retrieval of all {@link Student} entries.
     * Verifies that the service retrieves the expected list of students.
     */
    @Test
    void testGetAllStudents() {
        List<Student> studentList = Collections.singletonList(student);
        when(studentRepository.findAll()).thenReturn(studentList);

        List<Student> result = studentService.getAllStudents();

        assertThat(result).containsExactly(student);
        verify(studentRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of {@link Student} by its ID.
     * Verifies that the correct student is returned when found.
     */
    @Test
    void testGetStudentById() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));

        Optional<Student> result = studentService.getStudentById(1L);

        assertThat(result).isPresent().contains(student);
        verify(studentRepository, times(1)).findById(1L);
    }

    /**
     * Tests retrieval of {@link Student} by a non-existent ID.
     * Verifies that an empty Optional is returned when the ID is not found.
     */
    @Test
    void testGetStudentById_NotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Student> result = studentService.getStudentById(1L);

        assertThat(result).isEmpty();
        verify(studentRepository, times(1)).findById(1L);
    }

    /**
     * Tests updating an existing {@link Student} entry.
     * Verifies that the student is updated with the new details provided.
     */
    @Test
    void testUpdateStudent() {
        Student updatedDetails = TestDataHelper.createStudent("Scott",
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

        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(updatedDetails);

        Student updatedStudent = studentService.updateStudent(1L, updatedDetails);

        assertThat(updatedStudent.getGuardianFirstName()).isEqualTo(updatedDetails.getGuardianFirstName());
        assertThat(updatedStudent.getGuardianLastName()).isEqualTo(updatedDetails.getGuardianLastName());
        assertThat(updatedStudent.getGuardianContactNumber()).isEqualTo(updatedDetails.getGuardianContactNumber());
        assertThat(updatedStudent.getGuardianEmail()).isEqualTo(updatedDetails.getGuardianEmail());
        assertThat(updatedStudent.getContactNumber()).isEqualTo(updatedDetails.getContactNumber());
        assertThat(updatedStudent.getEmail()).isEqualTo(updatedDetails.getEmail());
        assertThat(updatedStudent.getStatus()).isEqualTo(updatedDetails.getStatus());
        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).save(student);
    }

    /**
     * Tests updating a {@link Student} with a non-existent ID.
     * Verifies that a {@link RuntimeException} is thrown when the student is not found.
     */
    @Test
    void testUpdateStudent_NotFound() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> studentService.updateStudent(1L, student))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Student not found with id 1");
        verify(studentRepository, times(1)).findById(1L);
        verify(studentRepository, never()).save(any(Student.class));
    }

    /**
     * Tests the deletion of a {@link Student} entry by its ID.
     * Verifies that the student is deleted from the repository.
     */
    @Test
    void testDeleteStudent() {
        doNothing().when(studentRepository).deleteById(anyLong());

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).deleteById(1L);
    }
}

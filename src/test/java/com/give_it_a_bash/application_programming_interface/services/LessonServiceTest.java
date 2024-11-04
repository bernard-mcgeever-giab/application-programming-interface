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

import com.give_it_a_bash.application_programming_interface.entities.Lesson;
import com.give_it_a_bash.application_programming_interface.repositories.LessonRepository;
import com.give_it_a_bash.application_programming_interface.testData.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link LessonService} class.
 *
 * These tests cover the CRUD operations provided by LessonService.
 * Each test verifies the expected behavior when interacting with the {@link LessonRepository}.
 */
class LessonServiceTest {

    @Mock
    private LessonRepository lessonRepository;

    @InjectMocks
    private LessonService lessonService;

    private Lesson lesson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        lesson = TestDataHelper.getLESSON();
    }

    /**
     * Tests the creation of a new {@link Lesson} entry.
     * Verifies that the lesson is saved in the repository.
     */
    @Test
    void testCreateLesson() {
        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);

        Lesson createdLesson = lessonService.createLesson(lesson);

        assertThat(createdLesson).isEqualTo(lesson);
        verify(lessonRepository, times(1)).save(lesson);
    }

    /**
     * Tests retrieval of all {@link Lesson} entries.
     * Verifies that the service retrieves the expected list of lessons from the repository.
     */
    @Test
    void testGetAllLessons() {
        List<Lesson> lessons = Collections.singletonList(lesson);
        when(lessonRepository.findAll()).thenReturn(lessons);

        List<Lesson> result = lessonService.getAllLessons();

        assertThat(result).containsExactly(lesson);
        verify(lessonRepository, times(1)).findAll();
    }

    /**
     * Tests retrieval of a {@link Lesson} by its ID.
     * Verifies that the correct lesson is returned when found.
     */
    @Test
    void testGetLessonById() {
        when(lessonRepository.findById(anyLong())).thenReturn(Optional.of(lesson));

        Optional<Lesson> result = lessonService.getLessonById(1L);

        assertThat(result).isPresent().contains(lesson);
        verify(lessonRepository, times(1)).findById(1L);
    }

    /**
     * Tests retrieval of a {@link Lesson} by a non-existent ID.
     * Verifies that an empty Optional is returned when the ID is not found.
     */
    @Test
    void testGetLessonById_NotFound() {
        when(lessonRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Lesson> result = lessonService.getLessonById(1L);

        assertThat(result).isEmpty();
        verify(lessonRepository, times(1)).findById(1L);
    }

    /**
     * Tests updating an existing {@link Lesson} entry.
     * Verifies that the lesson is updated with the new details provided.
     */
    @Test
    void testUpdateLesson() {
        Lesson updatedDetails = TestDataHelper.createLesson(LocalDateTime.of(2024, 12, 15, 14, 0),
                LocalDateTime.of(2024, 12, 15, 15, 30));

        when(lessonRepository.findById(anyLong())).thenReturn(Optional.of(lesson));
        when(lessonRepository.save(any(Lesson.class))).thenReturn(updatedDetails);

        Lesson updatedLesson = lessonService.updateLesson(1L, updatedDetails);

        assertThat(updatedLesson.getSubject()).isEqualTo(updatedDetails.getSubject());
        assertThat(updatedLesson.getTeacher()).isEqualTo(updatedDetails.getTeacher());
        assertThat(updatedLesson.getStartTime()).isEqualTo(updatedDetails.getStartTime());
        assertThat(updatedLesson.getEndTime()).isEqualTo(updatedDetails.getEndTime());
        verify(lessonRepository, times(1)).findById(1L);
        verify(lessonRepository, times(1)).save(lesson);
    }

    /**
     * Tests updating a {@link Lesson} with a non-existent ID.
     * Verifies that a {@link RuntimeException} is thrown when the lesson is not found.
     */
    @Test
    void testUpdateLesson_NotFound() {
        when(lessonRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> lessonService.updateLesson(1L, lesson))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Lesson not found with id 1");
        verify(lessonRepository, times(1)).findById(1L);
        verify(lessonRepository, never()).save(any(Lesson.class));
    }

    /**
     * Tests the deletion of a {@link Lesson} entry by its ID.
     * Verifies that the lesson is deleted from the repository.
     */
    @Test
    void testDeleteLesson() {
        doNothing().when(lessonRepository).deleteById(anyLong());

        lessonService.deleteLesson(1L);

        verify(lessonRepository, times(1)).deleteById(1L);
    }
}

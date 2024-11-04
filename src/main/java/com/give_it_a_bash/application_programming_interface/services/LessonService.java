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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing lessons taught by teachers on specific subjects.
 * This class provides methods for creating, retrieving, updating, and deleting lessons.
 */
@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    /**
     * Creates a new Lesson entry.
     *
     * @param lesson the Lesson to be created
     * @return the created Lesson
     */
    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    /**
     * Retrieves all Lesson entries.
     *
     * @return a list of all Lessons
     */
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    /**
     * Retrieves Lesson by its ID.
     *
     * @param id the ID of the Lesson
     * @return an Optional containing the Lesson if found
     */
    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }

    /**
     * Updates an existing Lesson entry.
     *
     * @param id the ID of the Lesson to be updated
     * @param lessonDetails the new details for the Lesson
     * @return the updated Lesson
     */
    public Lesson updateLesson(Long id, Lesson lessonDetails) {
        return lessonRepository.findById(id).map(lesson -> {
            lesson.setSubject(lessonDetails.getSubject());
            lesson.setTeacher(lessonDetails.getTeacher());
            lesson.setStartTime(lessonDetails.getStartTime());
            lesson.setEndTime(lessonDetails.getEndTime());
            lesson.setStudents(lessonDetails.getStudents());
            return lessonRepository.save(lesson);
        }).orElseThrow(() -> new RuntimeException("Lesson not found with id " + id));
    }

    /**
     * Deletes a Lesson entry by its ID.
     *
     * @param id the ID of the Lesson to be deleted
     */
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}

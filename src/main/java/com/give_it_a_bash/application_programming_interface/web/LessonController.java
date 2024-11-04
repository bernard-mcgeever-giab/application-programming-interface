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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling requests related to Lesson.
 * This class exposes RESTful endpoints for creating, retrieving, updating, and deleting lesson entities.
 */
@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    /**
     * Creates a new Lesson entry.
     *
     * @param lesson the Lesson to be created
     * @return ResponseEntity containing the created Lesson and HTTP status
     */
    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
        Lesson createdLesson = lessonService.createLesson(lesson);
        return new ResponseEntity<>(createdLesson, HttpStatus.CREATED);
    }

    /**
     * Retrieves all Lesson entries.
     *
     * @return ResponseEntity containing a list of all Lessons and HTTP status
     */
    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    /**
     * Retrieves Lesson by its ID.
     *
     * @param id the ID of the Lesson
     * @return ResponseEntity containing the Lesson if found, otherwise a NOT_FOUND status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        Optional<Lesson> lesson = lessonService.getLessonById(id);
        return lesson.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Updates an existing Lesson entry.
     *
     * @param id the ID of the Lesson to be updated
     * @param lessonDetails the new details for the Lesson
     * @return ResponseEntity containing the updated Lesson and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id,
                                               @RequestBody Lesson lessonDetails) {
        try {
            Lesson updatedLesson = lessonService.updateLesson(id, lessonDetails);
            return new ResponseEntity<>(updatedLesson, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes a Lesson entry by its ID.
     *
     * @param id the ID of the Lesson to be deleted
     * @return ResponseEntity with HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        try {
            lessonService.deleteLesson(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling requests related to Teacher.
 * This class exposes RESTful endpoints for creating, retrieving, updating, and deleting teacher entities.
 */
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * Creates a new Teacher entry.
     *
     * @param teacher the Teacher to be created
     * @return ResponseEntity containing the created Teacher and HTTP status
     */
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.createTeacher(teacher);
        return new ResponseEntity<>(createdTeacher, HttpStatus.CREATED);
    }

    /**
     * Retrieves all Teacher entries.
     *
     * @return ResponseEntity containing a list of all Teachers and HTTP status
     */
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    /**
     * Retrieves Teacher by its ID.
     *
     * @param id the ID of the Teacher
     * @return ResponseEntity containing the Teacher if found, otherwise a NOT_FOUND status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        Optional<Teacher> teacher = teacherService.getTeacherById(id);
        return teacher.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Updates an existing Teacher entry.
     *
     * @param id the ID of the Teacher to be updated
     * @param teacherDetails the new details for the Teacher
     * @return ResponseEntity containing the updated Teacher and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id,
                                                 @RequestBody Teacher teacherDetails) {
        try {
            Teacher updatedTeacher = teacherService.updateTeacher(id, teacherDetails);
            return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes a Teacher entry by its ID.
     *
     * @param id the ID of the Teacher to be deleted
     * @return ResponseEntity with HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

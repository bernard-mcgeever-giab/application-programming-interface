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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing academic subjects offered by the school.
 * This class provides methods for creating, retrieving, updating, and deleting subjects.
 */
@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * Creates a new Subject entry.
     *
     * @param subject the Subject to be created
     * @return the created Subject
     */
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    /**
     * Retrieves all Subject entries.
     *
     * @return a list of all Subjects
     */
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    /**
     * Retrieves Subject by its ID.
     *
     * @param id the ID of the Subject
     * @return an Optional containing the Subject if found
     */
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    /**
     * Updates an existing Subject entry.
     *
     * @param id the ID of the Subject to be updated
     * @param subjectDetails the new details for the Subject
     * @return the updated Subject
     */
    public Subject updateSubject(Long id, Subject subjectDetails) {
        return subjectRepository.findById(id).map(subject -> {
            subject.setName(subjectDetails.getName());
            subject.setSchoolData(subjectDetails.getSchoolData());
            subject.setTeacher(subjectDetails.getTeacher());
            return subjectRepository.save(subject);
        }).orElseThrow(() -> new RuntimeException("Subject not found with id " + id));
    }

    /**
     * Deletes a Subject entry by its ID.
     *
     * @param id the ID of the Subject to be deleted
     */
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}

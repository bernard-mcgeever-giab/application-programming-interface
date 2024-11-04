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

import com.give_it_a_bash.application_programming_interface.entities.Student;
import com.give_it_a_bash.application_programming_interface.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing students who are mutants with unique powers.
 * This class provides methods for creating, retrieving, updating, and deleting students.
 */
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Creates a new Student entry.
     *
     * @param student the Student to be created
     * @return the created Student
     */
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    /**
     * Retrieves all Student entries.
     *
     * @return a list of all Students
     */
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    /**
     * Retrieves Student by its ID.
     *
     * @param id the ID of the Student
     * @return an Optional containing the Student if found
     */
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    /**
     * Updates an existing Student entry.
     *
     * @param id the ID of the Student to be updated
     * @param studentDetails the new details for the Student
     * @return the updated Student
     */
    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setGuardianFirstName(studentDetails.getGuardianFirstName());
            student.setGuardianLastName(studentDetails.getGuardianLastName());
            student.setGuardianContactNumber(studentDetails.getGuardianContactNumber());
            student.setGuardianEmail(studentDetails.getGuardianEmail());
            student.setContactNumber(studentDetails.getContactNumber());
            student.setEmail(studentDetails.getEmail());
            student.setStatus(studentDetails.getStatus());
            return studentRepository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found with id " + id));
    }

    /**
     * Deletes a Student entry by its ID.
     *
     * @param id the ID of the Student to be deleted
     */
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

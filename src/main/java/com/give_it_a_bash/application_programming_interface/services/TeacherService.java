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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing teachers with unique powers and characteristics.
 * This class provides methods for creating, retrieving, updating, and deleting teachers.
 */
@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    /**
     * Creates a new Teacher entry.
     *
     * @param teacher the Teacher to be created
     * @return the created Teacher
     */
    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    /**
     * Retrieves all Teacher entries.
     *
     * @return a list of all Teachers
     */
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    /**
     * Retrieves Teacher by its ID.
     *
     * @param id the ID of the Teacher
     * @return an Optional containing the Teacher if found
     */
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    /**
     * Updates an existing Teacher entry.
     *
     * @param id the ID of the Teacher to be updated
     * @param teacherDetails the new details for the Teacher
     * @return the updated Teacher
     */
    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        return teacherRepository.findById(id).map(teacher -> {
            teacher.setId(teacherDetails.getId());
            teacher.setSchoolData(teacherDetails.getSchoolData());
            teacher.setFirstName(teacherDetails.getFirstName());
            teacher.setLastName(teacherDetails.getLastName());
            teacher.setAlias(teacherDetails.getAlias());
            teacher.setPower(teacherDetails.getPower());
            teacher.setMissionHistory(teacherDetails.getMissionHistory());
            teacher.setIsActive(teacherDetails.getIsActive());
            teacher.setEmail(teacherDetails.getEmail());
            teacher.setPhoneNumber(teacherDetails.getPhoneNumber());
            teacher.setAddress(teacherDetails.getAddress());
            teacher.setQualifications(teacherDetails.getQualifications());
            teacher.setYearsOfExperience(teacherDetails.getYearsOfExperience());
            teacher.setDepartment(teacherDetails.getDepartment());

            return teacherRepository.save(teacher);
        }).orElseThrow(() -> new RuntimeException("Teacher not found with id " + id));
    }

    /**
     * Deletes a Teacher entry by its ID.
     *
     * @param id the ID of the Teacher to be deleted
     */
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}

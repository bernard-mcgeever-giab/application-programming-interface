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

import com.give_it_a_bash.application_programming_interface.entities.SchoolData;
import com.give_it_a_bash.application_programming_interface.repositories.SchoolDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing SchoolData, including associated students, teachers, subjects, and facilities.
 * This class provides methods for creating, retrieving, updating, and deleting school data entities.
 */
@Service
public class SchoolDataService {

    @Autowired
    private SchoolDataRepository schoolDataRepository;

    /**
     * Creates a new SchoolData entry.
     *
     * @param schoolData the SchoolData to be created
     * @return the created SchoolData
     */
    public SchoolData createSchoolData(SchoolData schoolData) {
        return schoolDataRepository.save(schoolData);
    }

    /**
     * Retrieves all SchoolData entries.
     *
     * @return a list of all SchoolData
     */
    public List<SchoolData> getAllSchoolData() {
        return schoolDataRepository.findAll();
    }

    /**
     * Retrieves SchoolData by its ID.
     *
     * @param id the ID of the SchoolData
     * @return an Optional containing the SchoolData if found
     */
    public Optional<SchoolData> getSchoolDataById(Long id) {
        return schoolDataRepository.findById(id);
    }

    /**
     * Updates an existing SchoolData entry.
     *
     * @param id the ID of the SchoolData to be updated
     * @param schoolDataDetails the new details for the SchoolData
     * @return the updated SchoolData
     */
    public SchoolData updateSchoolData(Long id, SchoolData schoolDataDetails) {
        return schoolDataRepository.findById(id).map(schoolData -> {
            schoolData.setSchoolName(schoolDataDetails.getSchoolName());
            schoolData.setLocation(schoolDataDetails.getLocation());
            schoolData.setMotto(schoolDataDetails.getMotto());
            schoolData.setYearEstablished(schoolDataDetails.getYearEstablished());
            schoolData.setAffiliation(schoolDataDetails.getAffiliation());
            schoolData.setContactInfo(schoolDataDetails.getContactInfo());
            schoolData.setActive(schoolDataDetails.isActive());
            schoolData.setStudents(schoolDataDetails.getStudents());
            schoolData.setTeachers(schoolDataDetails.getTeachers());
            schoolData.setSubjects(schoolDataDetails.getSubjects());
            schoolData.setFacilities(schoolDataDetails.getFacilities());
            return schoolDataRepository.save(schoolData);
        }).orElseThrow(() -> new RuntimeException("School data not found with id " + id));
    }

    /**
     * Deletes a SchoolData entry by its ID.
     *
     * @param id the ID of the SchoolData to be deleted
     */
    public void deleteSchoolData(Long id) {
        schoolDataRepository.deleteById(id);
    }
}

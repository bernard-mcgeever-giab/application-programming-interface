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

import com.give_it_a_bash.application_programming_interface.entities.SchoolData;
import com.give_it_a_bash.application_programming_interface.services.SchoolDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling requests related to SchoolData.
 * This class exposes RESTful endpoints for creating, retrieving, updating, and deleting school data entities.
 */
@RestController
@RequestMapping("/api/schooldata")
public class SchoolDataController {

    @Autowired
    private SchoolDataService schoolDataService;

    /**
     * Creates a new SchoolData entry.
     *
     * @param schoolData the SchoolData to be created
     * @return ResponseEntity containing the created SchoolData and HTTP status
     */
    @PostMapping
    public ResponseEntity<SchoolData> createSchoolData(@RequestBody SchoolData schoolData) {
        SchoolData createdSchoolData = schoolDataService.createSchoolData(schoolData);
        return new ResponseEntity<>(createdSchoolData, HttpStatus.CREATED);
    }

    /**
     * Retrieves all SchoolData entries.
     *
     * @return ResponseEntity containing a list of all SchoolData and HTTP status
     */
    @GetMapping
    public ResponseEntity<List<SchoolData>> getAllSchoolData() {
        List<SchoolData> schoolDataList = schoolDataService.getAllSchoolData();
        return new ResponseEntity<>(schoolDataList, HttpStatus.OK);
    }

    /**
     * Retrieves SchoolData by its ID.
     *
     * @param id the ID of the SchoolData
     * @return ResponseEntity containing the SchoolData if found, otherwise a NOT_FOUND status
     */
    @GetMapping("/{id}")
    public ResponseEntity<SchoolData> getSchoolDataById(@PathVariable("id") Long id) {
        Optional<SchoolData> schoolData = schoolDataService.getSchoolDataById(id);
        return schoolData.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Updates an existing SchoolData entry.
     *
     * @param id the ID of the SchoolData to be updated
     * @param schoolDataDetails the new details for the SchoolData
     * @return ResponseEntity containing the updated SchoolData and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<SchoolData> updateSchoolData(@PathVariable("id") Long id,
                                                       @RequestBody SchoolData schoolDataDetails) {
        try {
            SchoolData updatedSchoolData = schoolDataService.updateSchoolData(id, schoolDataDetails);
            return new ResponseEntity<>(updatedSchoolData, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes a SchoolData entry by its ID.
     *
     * @param id the ID of the SchoolData to be deleted
     * @return ResponseEntity with HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchoolData(@PathVariable("id") Long id) {
        try {
            schoolDataService.deleteSchoolData(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

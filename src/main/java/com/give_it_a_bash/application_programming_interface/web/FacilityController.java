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

import com.give_it_a_bash.application_programming_interface.entities.Facility;
import com.give_it_a_bash.application_programming_interface.services.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling requests related to Facility.
 * This class exposes RESTful endpoints for creating, retrieving, updating, and deleting facility entities.
 */
@RestController
@RequestMapping("/api/facilities")
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    /**
     * Creates a new Facility entry.
     *
     * @param facility the Facility to be created
     * @return ResponseEntity containing the created Facility and HTTP status
     */
    @PostMapping
    public ResponseEntity<Facility> createFacility(@RequestBody Facility facility) {
        Facility createdFacility = facilityService.createFacility(facility);
        return new ResponseEntity<>(createdFacility, HttpStatus.CREATED);
    }

    /**
     * Retrieves all Facility entries.
     *
     * @return ResponseEntity containing a list of all Facilities and HTTP status
     */
    @GetMapping
    public ResponseEntity<List<Facility>> getAllFacilities() {
        List<Facility> facilities = facilityService.getAllFacilities();
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }

    /**
     * Retrieves Facility by its ID.
     *
     * @param id the ID of the Facility
     * @return ResponseEntity containing the Facility if found, otherwise a NOT_FOUND status
     */
    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable Long id) {
        Optional<Facility> facility = facilityService.getFacilityById(id);
        return facility.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Updates an existing Facility entry.
     *
     * @param id the ID of the Facility to be updated
     * @param facilityDetails the new details for the Facility
     * @return ResponseEntity containing the updated Facility and HTTP status
     */
    @PutMapping("/{id}")
    public ResponseEntity<Facility> updateFacility(@PathVariable Long id,
                                                   @RequestBody Facility facilityDetails) {
        try {
            Facility updatedFacility = facilityService.updateFacility(id, facilityDetails);
            return new ResponseEntity<>(updatedFacility, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Deletes a Facility entry by its ID.
     *
     * @param id the ID of the Facility to be deleted
     * @return ResponseEntity with HTTP status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacility(@PathVariable Long id) {
        try {
            facilityService.deleteFacility(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

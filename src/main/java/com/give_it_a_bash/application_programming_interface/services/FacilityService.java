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

import com.give_it_a_bash.application_programming_interface.entities.Facility;
import com.give_it_a_bash.application_programming_interface.repositories.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing facilities within a school campus.
 * This class provides methods for creating, retrieving, updating, and deleting facilities.
 */
@Service
public class FacilityService {

    @Autowired
    private FacilityRepository facilityRepository;

    /**
     * Creates a new Facility entry.
     *
     * @param facility the Facility to be created
     * @return the created Facility
     */
    public Facility createFacility(Facility facility) {
        return facilityRepository.save(facility);
    }

    /**
     * Retrieves all Facility entries.
     *
     * @return a list of all Facilities
     */
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }

    /**
     * Retrieves Facility by its ID.
     *
     * @param id the ID of the Facility
     * @return an Optional containing the Facility if found
     */
    public Optional<Facility> getFacilityById(Long id) {
        return facilityRepository.findById(id);
    }

    /**
     * Updates an existing Facility entry.
     *
     * @param id the ID of the Facility to be updated
     * @param facilityDetails the new details for the Facility
     * @return the updated Facility
     */
    public Facility updateFacility(Long id, Facility facilityDetails) {
        return facilityRepository.findById(id).map(facility -> {
            facility.setName(facilityDetails.getName());
            facility.setType(facilityDetails.getType());
            facility.setDescription(facilityDetails.getDescription());
            facility.setAccessible(facilityDetails.isAccessible());
            facility.setLocationWithinCampus(facilityDetails.getLocationWithinCampus());
            facility.setCapacity(facilityDetails.getCapacity());
            facility.setOperational(facilityDetails.isOperational());
            facility.setSchoolData(facilityDetails.getSchoolData());
            return facilityRepository.save(facility);
        }).orElseThrow(() -> new RuntimeException("Facility not found with id " + id));
    }

    /**
     * Deletes a Facility entry by its ID.
     *
     * @param id the ID of the Facility to be deleted
     */
    public void deleteFacility(Long id) {
        facilityRepository.deleteById(id);
    }
}

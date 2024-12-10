package com.give_it_a_bash.application_programming_interface.intergration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.give_it_a_bash.application_programming_interface.entities.Facility;
import com.give_it_a_bash.application_programming_interface.entities.FacilityType;
import com.give_it_a_bash.application_programming_interface.entities.SchoolData;
import com.give_it_a_bash.application_programming_interface.entities.Teacher;
import com.give_it_a_bash.application_programming_interface.repositories.FacilityRepository;
import com.give_it_a_bash.application_programming_interface.repositories.SchoolDataRepository;
import com.give_it_a_bash.application_programming_interface.repositories.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the Facility API endpoints.
 * This class tests CRUD operations for the Facility entity, including:
 * <ul>
 *     <li>Creating a new Facility record</li>
 *     <li>Retrieving all Facility records</li>
 *     <li>Retrieving a specific Facility record by ID</li>
 *     <li>Updating an existing Facility record</li>
 *     <li>Deleting a Facility record</li>
 * </ul>
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:facility_it;DB_CLOSE_DELAY=-1")
class FacilityIT {

    @Autowired
    private MockMvc mockMvc; // MockMvc to perform HTTP requests

    @Autowired
    private FacilityRepository facilityRepository; // Repository for interacting with the database

    @Autowired
    private SchoolDataRepository schoolDataRepository; // Repository for interacting with the database

    @Autowired
    private ObjectMapper objectMapper; // ObjectMapper to convert objects to JSON

    private Facility facility; // A class level test instance of Facility

    private SchoolData schoolData; // A class level test instance of SchoolData


    /**
     * Set up the test environment before each test.
     * This method deletes all records in the database and inserts a sample Facility record.
     */
    @BeforeEach
    void setUp() {
        facilityRepository.deleteAll();
        schoolDataRepository.deleteAll();

        schoolData = SchoolData.builder()
                .schoolName("Xavier Institute for Higher Learning")
                .location("1407 Graymalkin Lane, Salem Center, NY")
                .motto("Mutatis Mutandis")
                .yearEstablished(1963)
                .affiliation("Mutant Education and Research")
                .contactInfo("+1-555-XAVIER")
                .isActive(true)
                .build();

        facility = Facility.builder()
                .name("Cerebro Chamber")
                .type(FacilityType.LAB_FACILITY)
                .description("A chamber designed to amplify telepathic abilities.")
                .isAccessible(true)
                .locationWithinCampus("Basement Level")
                .capacity(5)
                .schoolData(schoolData)
                .isOperational(true)
                .build();

        schoolData = schoolDataRepository.save(schoolData);
        facility = facilityRepository.save(facility);
    }

    /**
     * Test creating a new Facility record via the POST /api/facilities endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void createFacility() throws Exception {
        Facility newFacility = Facility.builder()
                .name("Danger Room")
                .type(FacilityType.LAB_FACILITY)
                .description("A virtual training facility for mutants.")
                .isAccessible(true)
                .locationWithinCampus("West Wing")
                .capacity(10)
                .schoolData(schoolData)
                .isOperational(true)
                .build();

        mockMvc.perform(post("/api/facilities")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newFacility)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(newFacility.getName()))
                .andExpect(jsonPath("$.type").value(newFacility.getType().name()))
                .andExpect(jsonPath("$.description").value(newFacility.getDescription()))
                .andExpect(jsonPath("$.accessible").value(newFacility.isAccessible()))
                .andExpect(jsonPath("$.locationWithinCampus").value(newFacility.getLocationWithinCampus()))
                .andExpect(jsonPath("$.capacity").value(newFacility.getCapacity()))
                .andExpect(jsonPath("$.schoolData.id").value(newFacility.getSchoolData().getId()))
                .andExpect(jsonPath("$.operational").value(newFacility.isOperational()));
    }

    /**
     * Test retrieving all Facility records via the GET /api/facilities endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getAllFacilities() throws Exception {
        mockMvc.perform(get("/api/facilities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(facility.getName()))
                .andExpect(jsonPath("$[0].type").value(facility.getType().name()))
                .andExpect(jsonPath("$[0].description").value(facility.getDescription()))
                .andExpect(jsonPath("$[0].accessible").value(facility.isAccessible()))
                .andExpect(jsonPath("$[0].locationWithinCampus").value(facility.getLocationWithinCampus()))
                .andExpect(jsonPath("$[0].capacity").value(facility.getCapacity()))
                .andExpect(jsonPath("$[0].schoolData.id").value(facility.getSchoolData().getId()))
                .andExpect(jsonPath("$[0].operational").value(facility.isOperational()));
    }

    /**
     * Test retrieving a specific Facility record by its ID via the GET /api/facilities/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getFacilityById() throws Exception {
        mockMvc.perform(get("/api/facilities/" + facility.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(facility.getName()))
                .andExpect(jsonPath("$.type").value(facility.getType().name()))
                .andExpect(jsonPath("$.description").value(facility.getDescription()))
                .andExpect(jsonPath("$.accessible").value(facility.isAccessible()))
                .andExpect(jsonPath("$.locationWithinCampus").value(facility.getLocationWithinCampus()))
                .andExpect(jsonPath("$.capacity").value(facility.getCapacity()))
                .andExpect(jsonPath("$.schoolData.id").value(facility.getSchoolData().getId()))
                .andExpect(jsonPath("$.operational").value(facility.isOperational()));
    }

    /**
     * Test handling the case where a Facility record with a non-existent ID is requested.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void getFacilityByIdNotFound() throws Exception {
        mockMvc.perform(get("/api/facilities/9999"))
                .andExpect(status().isNotFound());
    }

    /**
     * Test updating an existing Facility record via the PUT /api/facilities/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void updateFacility() throws Exception {
        Facility updatedFacility = Facility.builder()
                .name("Cerebro Chamber")
                .type(FacilityType.LAB_FACILITY)
                .description("A chamber designed to amplify telepathic abilities.")
                .isAccessible(true)
                .locationWithinCampus("Basement Level")
                .capacity(5)
                .schoolData(schoolData)
                .isOperational(false)
                .build();

        updatedFacility.setId(facility.getId());

        mockMvc.perform(put("/api/facilities/" + facility.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFacility)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operational").value(updatedFacility.isOperational()));
    }

    /**
     * Test deleting an existing Facility record via the DELETE /api/facilities/{id} endpoint.
     * @throws Exception If an error occurs during the HTTP request
     */
    @Test
    void deleteFacility() throws Exception {
        mockMvc.perform(delete("/api/facilities/" + facility.getId()))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/facilities/" + facility.getId()))
                .andExpect(status().isNotFound());
    }
}

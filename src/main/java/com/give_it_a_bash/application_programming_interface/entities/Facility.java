package com.give_it_a_bash.application_programming_interface.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a facility within a school campus.
 */
@Entity
@Getter
@Setter
public class Facility {

    /**
     * Unique identifier for the facility.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Name of the facility.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Type of the facility.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FacilityType type;

    /**
     * Description of the facility.
     */
    private String description;

    /**
     * Indicates whether the facility is accessible.
     */
    private boolean isAccessible;

    /**
     * Location of the facility within the campus.
     */
    private String locationWithinCampus;

    /**
     * Maximum capacity of the facility.
     */
    @Column(nullable = false)
    private int capacity;

    /**
     * The school data associated with the facility.
     */
    @ManyToOne
    @JoinColumn(name = "school_data_id", nullable = false)
    private SchoolData schoolData;

    /**
     * Indicates whether the facility is currently operational.
     */
    private boolean isOperational;

    // No-argument constructor
    public Facility() {
    }

    @Builder
    public Facility(String name, FacilityType type, String description, boolean isAccessible,
                    String locationWithinCampus, int capacity, boolean isOperational,
                    SchoolData schoolData) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.isAccessible = isAccessible;
        this.locationWithinCampus = locationWithinCampus;
        this.capacity = capacity;
        this.isOperational = isOperational;
        this.schoolData = schoolData;
    }
}

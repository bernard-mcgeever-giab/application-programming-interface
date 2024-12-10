/*
 * Copyright (c) 2024 Give It A Bash
 *
 * This file is part of Give It A Bash proprietary software.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 * Proprietary and confidential.
 *
 * Created and maintained by Give It A Bash.
 */

package com.give_it_a_bash.application_programming_interface.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a Mutant entity in the system.
 * <p>
 * This class serves as a base for different types of mutants, storing common properties
 * such as personal information, power details, and mission history.
 * </p>
 *
 * @see Student
 * @see Teacher
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "mutant_type")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
public abstract class Mutant {

    /**
     * Unique identifier for the mutant.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    /**
     * Reference to the associated SchoolData entity.
     */
    @ManyToOne
    @JoinColumn(name = "school_data_id")
    private SchoolData schoolData;

    /**
     * First name of the mutant.
     */
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    /**
     * Last name of the mutant.
     */
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    /**
     * Alias of the mutant, representing their superhero identity.
     */
    private String alias;

    /**
     * The power associated with the mutant.
     */
    @Embedded
    @AttributeOverride(name = "name", column = @Column(name = "power_name", nullable = true))
    private Power power;

    /**
     * History of missions undertaken by the mutant.
     */
    @ElementCollection
    @CollectionTable(name = "mutant_mission_history", joinColumns = @JoinColumn(name = "mutant_id"))
    @Column(name = "mission_name")
    private List<String> missionHistory;

    /**
     * Indicates whether the mutant is currently active.
     */
    @Column(name = "is_mutant_active", nullable = false)
    @NotNull(message = "Active status must be defined")
    private Boolean isActive;

    public Mutant(SchoolData schoolData,
                  String firstName,
                  String lastName,
                  String alias,
                  Power power,
                  List<String> missionHistory,
                  Boolean isActive) {
        this.schoolData = schoolData;
        this.firstName = firstName;
        this.lastName = lastName;
        this.alias = alias;
        this.power = power;
        this.missionHistory = missionHistory != null ? missionHistory : new ArrayList<>();;
        this.isActive = isActive;
    }
}

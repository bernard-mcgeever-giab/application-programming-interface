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

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents the power of a mutant, encapsulating its properties such as name,
 * power level, description, category, active status, control level, and source of origin.
 */
@Embeddable
@Getter
@Setter
public class Power {

    /**
     * The name of the power.
     */
    @JsonProperty("powerName")
    private String name;

    /**
     * The level of the power, indicating its strength or potency.
     */
    private int powerLevel;

    /**
     * A brief description of the power.
     */
    @JsonProperty("powerDescription")
    private String description;

    /**
     * The category of the power (e.g., elemental, psychic, etc.).
     */
    @JsonProperty("powerCategory")
    private String category;

    /**
     * Indicates whether the power is currently active.
     */
    @Column(name = "is_power_active", nullable = false)
    @JsonProperty("isPowerActive")
    private boolean isActive;

    /**
     * The level of control the mutant has over their power.
     */
    private int controlLevel;

    /**
     * The source of origin for the power, indicating how it was acquired.
     */
    private PowerSource originSource;

    /**
     * Custom constructor for the Power class.
     *
     * @param name         The name of the power.
     * @param powerLevel   The level of the power.
     * @param description  A brief description of the power.
     * @param category     The category of the power.
     * @param isActive     Whether the power is active.
     * @param controlLevel The level of control over the power.
     * @param originSource The source of the power.
     */
    @Builder
    public Power(String name, int powerLevel, String description, String category, boolean isActive, int controlLevel,
                 PowerSource originSource) {
        this.name = name;
        this.powerLevel = powerLevel;
        this.description = description;
        this.category = category;
        this.isActive = isActive;
        this.controlLevel = controlLevel;
        this.originSource = originSource;
    }

    // No-args constructor (required by Hibernate)
    public Power() {
    }

}

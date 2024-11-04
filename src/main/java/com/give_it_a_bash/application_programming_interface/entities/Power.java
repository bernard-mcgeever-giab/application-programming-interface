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
@Builder
public class Power {

    /**
     * The name of the power.
     */
    private String name;

    /**
     * The level of the power, indicating its strength or potency.
     */
    private int powerLevel;

    /**
     * A brief description of the power.
     */
    private String description;

    /**
     * The category of the power (e.g., elemental, psychic, etc.).
     */
    private String category;

    /**
     * Indicates whether the power is currently active.
     */
    @Column(name = "is_power_active", nullable = false)
    private boolean isActive;

    /**
     * The level of control the mutant has over their power.
     */
    private int controlLevel;

    /**
     * The source of origin for the power, indicating how it was acquired.
     */
    private PowerSource originSource;
}

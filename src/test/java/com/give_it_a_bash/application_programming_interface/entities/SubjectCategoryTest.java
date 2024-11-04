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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SubjectCategoryTest {

    /**
     * Tests that the {@link SubjectCategory} enum values are correctly initialized.
     */
    @Test
    public void testSubjectCategoryValues() {
        assertThat(SubjectCategory.ACADEMIC.name()).isEqualTo("ACADEMIC");
        assertThat(SubjectCategory.ART.name()).isEqualTo("ART");
        assertThat(SubjectCategory.PHYSICAL_EDUCATION.name()).isEqualTo("PHYSICAL_EDUCATION");
        assertThat(SubjectCategory.TECHNOLOGY.name()).isEqualTo("TECHNOLOGY");
        assertThat(SubjectCategory.LANGUAGE.name()).isEqualTo("LANGUAGE");
        assertThat(SubjectCategory.SOCIAL_SCIENCE.name()).isEqualTo("SOCIAL_SCIENCE");
        assertThat(SubjectCategory.VOCATIONAL.name()).isEqualTo("VOCATIONAL");
        assertThat(SubjectCategory.HEALTH.name()).isEqualTo("HEALTH");
        assertThat(SubjectCategory.ENVIRONMENTAL_STUDIES.name()).isEqualTo("ENVIRONMENTAL_STUDIES");
        assertThat(SubjectCategory.BUSINESS.name()).isEqualTo("BUSINESS");
        assertThat(SubjectCategory.MATHEMATICS.name()).isEqualTo("MATHEMATICS");
        assertThat(SubjectCategory.COMPUTER_SCIENCE.name()).isEqualTo("COMPUTER_SCIENCE");
        assertThat(SubjectCategory.PHYSICS.name()).isEqualTo("PHYSICS");
        assertThat(SubjectCategory.CHEMISTRY.name()).isEqualTo("CHEMISTRY");
        assertThat(SubjectCategory.BIOLOGY.name()).isEqualTo("BIOLOGY");
        assertThat(SubjectCategory.ENGINEERING.name()).isEqualTo("ENGINEERING");
        assertThat(SubjectCategory.PSYCHOLOGY.name()).isEqualTo("PSYCHOLOGY");
        assertThat(SubjectCategory.PHILOSOPHY.name()).isEqualTo("PHILOSOPHY");
        assertThat(SubjectCategory.ECONOMICS.name()).isEqualTo("ECONOMICS");
        assertThat(SubjectCategory.FINANCE.name()).isEqualTo("FINANCE");
        assertThat(SubjectCategory.GOVERNMENT.name()).isEqualTo("GOVERNMENT");
        assertThat(SubjectCategory.LAW.name()).isEqualTo("LAW");
        assertThat(SubjectCategory.NUTRITION.name()).isEqualTo("NUTRITION");
        assertThat(SubjectCategory.AGRICULTURE.name()).isEqualTo("AGRICULTURE");
        assertThat(SubjectCategory.ASTRONOMY.name()).isEqualTo("ASTRONOMY");
        assertThat(SubjectCategory.STATISTICS.name()).isEqualTo("STATISTICS");
        assertThat(SubjectCategory.MUSIC.name()).isEqualTo("MUSIC");
        assertThat(SubjectCategory.DANCE.name()).isEqualTo("DANCE");
        assertThat(SubjectCategory.THEATER.name()).isEqualTo("THEATER");
        assertThat(SubjectCategory.FOREIGN_LANGUAGE.name()).isEqualTo("FOREIGN_LANGUAGE");
        assertThat(SubjectCategory.ARTIFICIAL_INTELLIGENCE.name()).isEqualTo("ARTIFICIAL_INTELLIGENCE");
        assertThat(SubjectCategory.ROBOTICS.name()).isEqualTo("ROBOTICS");
        assertThat(SubjectCategory.GRAPHIC_DESIGN.name()).isEqualTo("GRAPHIC_DESIGN");
        assertThat(SubjectCategory.JOURNALISM.name()).isEqualTo("JOURNALISM");
        assertThat(SubjectCategory.ARCHITECTURE.name()).isEqualTo("ARCHITECTURE");
    }

    /**
     * Tests that the {@link SubjectCategory} enum can be retrieved by its name.
     */
    @Test
    public void testSubjectCategoryByName() {
        assertThat(SubjectCategory.valueOf("ACADEMIC")).isEqualTo(SubjectCategory.ACADEMIC);
        assertThat(SubjectCategory.valueOf("ART")).isEqualTo(SubjectCategory.ART);
        assertThat(SubjectCategory.valueOf("PHYSICAL_EDUCATION")).isEqualTo(SubjectCategory.PHYSICAL_EDUCATION);
        assertThat(SubjectCategory.valueOf("TECHNOLOGY")).isEqualTo(SubjectCategory.TECHNOLOGY);
        assertThat(SubjectCategory.valueOf("LANGUAGE")).isEqualTo(SubjectCategory.LANGUAGE);
        assertThat(SubjectCategory.valueOf("SOCIAL_SCIENCE")).isEqualTo(SubjectCategory.SOCIAL_SCIENCE);
        assertThat(SubjectCategory.valueOf("VOCATIONAL")).isEqualTo(SubjectCategory.VOCATIONAL);
        assertThat(SubjectCategory.valueOf("HEALTH")).isEqualTo(SubjectCategory.HEALTH);
        assertThat(SubjectCategory.valueOf("ENVIRONMENTAL_STUDIES")).isEqualTo(SubjectCategory.ENVIRONMENTAL_STUDIES);
        assertThat(SubjectCategory.valueOf("BUSINESS")).isEqualTo(SubjectCategory.BUSINESS);
        assertThat(SubjectCategory.valueOf("MATHEMATICS")).isEqualTo(SubjectCategory.MATHEMATICS);
        assertThat(SubjectCategory.valueOf("COMPUTER_SCIENCE")).isEqualTo(SubjectCategory.COMPUTER_SCIENCE);
        assertThat(SubjectCategory.valueOf("PHYSICS")).isEqualTo(SubjectCategory.PHYSICS);
        assertThat(SubjectCategory.valueOf("CHEMISTRY")).isEqualTo(SubjectCategory.CHEMISTRY);
        assertThat(SubjectCategory.valueOf("BIOLOGY")).isEqualTo(SubjectCategory.BIOLOGY);
        assertThat(SubjectCategory.valueOf("ENGINEERING")).isEqualTo(SubjectCategory.ENGINEERING);
        assertThat(SubjectCategory.valueOf("PSYCHOLOGY")).isEqualTo(SubjectCategory.PSYCHOLOGY);
        assertThat(SubjectCategory.valueOf("PHILOSOPHY")).isEqualTo(SubjectCategory.PHILOSOPHY);
        assertThat(SubjectCategory.valueOf("ECONOMICS")).isEqualTo(SubjectCategory.ECONOMICS);
        assertThat(SubjectCategory.valueOf("FINANCE")).isEqualTo(SubjectCategory.FINANCE);
        assertThat(SubjectCategory.valueOf("GOVERNMENT")).isEqualTo(SubjectCategory.GOVERNMENT);
        assertThat(SubjectCategory.valueOf("LAW")).isEqualTo(SubjectCategory.LAW);
        assertThat(SubjectCategory.valueOf("NUTRITION")).isEqualTo(SubjectCategory.NUTRITION);
        assertThat(SubjectCategory.valueOf("AGRICULTURE")).isEqualTo(SubjectCategory.AGRICULTURE);
        assertThat(SubjectCategory.valueOf("ASTRONOMY")).isEqualTo(SubjectCategory.ASTRONOMY);
        assertThat(SubjectCategory.valueOf("STATISTICS")).isEqualTo(SubjectCategory.STATISTICS);
        assertThat(SubjectCategory.valueOf("MUSIC")).isEqualTo(SubjectCategory.MUSIC);
        assertThat(SubjectCategory.valueOf("DANCE")).isEqualTo(SubjectCategory.DANCE);
        assertThat(SubjectCategory.valueOf("THEATER")).isEqualTo(SubjectCategory.THEATER);
        assertThat(SubjectCategory.valueOf("FOREIGN_LANGUAGE")).isEqualTo(SubjectCategory.FOREIGN_LANGUAGE);
        assertThat(SubjectCategory.valueOf("ARTIFICIAL_INTELLIGENCE")).isEqualTo(SubjectCategory.ARTIFICIAL_INTELLIGENCE);
        assertThat(SubjectCategory.valueOf("ROBOTICS")).isEqualTo(SubjectCategory.ROBOTICS);
        assertThat(SubjectCategory.valueOf("GRAPHIC_DESIGN")).isEqualTo(SubjectCategory.GRAPHIC_DESIGN);
        assertThat(SubjectCategory.valueOf("JOURNALISM")).isEqualTo(SubjectCategory.JOURNALISM);
        assertThat(SubjectCategory.valueOf("ARCHITECTURE")).isEqualTo(SubjectCategory.ARCHITECTURE);
    }

    /**
     * Tests the ordinal values of the {@link SubjectCategory} enum.
     */
    @Test
    public void testSubjectCategoryOrdinals() {
        assertThat(SubjectCategory.ACADEMIC.ordinal()).isEqualTo(0);
        assertThat(SubjectCategory.ART.ordinal()).isEqualTo(1);
        assertThat(SubjectCategory.PHYSICAL_EDUCATION.ordinal()).isEqualTo(2);
        assertThat(SubjectCategory.TECHNOLOGY.ordinal()).isEqualTo(3);
        assertThat(SubjectCategory.LANGUAGE.ordinal()).isEqualTo(4);
        assertThat(SubjectCategory.SOCIAL_SCIENCE.ordinal()).isEqualTo(5);
        assertThat(SubjectCategory.VOCATIONAL.ordinal()).isEqualTo(6);
        assertThat(SubjectCategory.HEALTH.ordinal()).isEqualTo(7);
        assertThat(SubjectCategory.ENVIRONMENTAL_STUDIES.ordinal()).isEqualTo(8);
        assertThat(SubjectCategory.BUSINESS.ordinal()).isEqualTo(9);
        assertThat(SubjectCategory.MATHEMATICS.ordinal()).isEqualTo(10);
        assertThat(SubjectCategory.COMPUTER_SCIENCE.ordinal()).isEqualTo(11);
        assertThat(SubjectCategory.PHYSICS.ordinal()).isEqualTo(12);
        assertThat(SubjectCategory.CHEMISTRY.ordinal()).isEqualTo(13);
        assertThat(SubjectCategory.BIOLOGY.ordinal()).isEqualTo(14);
        assertThat(SubjectCategory.ENGINEERING.ordinal()).isEqualTo(15);
        assertThat(SubjectCategory.PSYCHOLOGY.ordinal()).isEqualTo(16);
        assertThat(SubjectCategory.PHILOSOPHY.ordinal()).isEqualTo(17);
        assertThat(SubjectCategory.ECONOMICS.ordinal()).isEqualTo(18);
        assertThat(SubjectCategory.FINANCE.ordinal()).isEqualTo(19);
        assertThat(SubjectCategory.GOVERNMENT.ordinal()).isEqualTo(20);
        assertThat(SubjectCategory.LAW.ordinal()).isEqualTo(21);
        assertThat(SubjectCategory.NUTRITION.ordinal()).isEqualTo(22);
        assertThat(SubjectCategory.AGRICULTURE.ordinal()).isEqualTo(23);
        assertThat(SubjectCategory.ASTRONOMY.ordinal()).isEqualTo(24);
        assertThat(SubjectCategory.STATISTICS.ordinal()).isEqualTo(25);
        assertThat(SubjectCategory.MUSIC.ordinal()).isEqualTo(26);
        assertThat(SubjectCategory.DANCE.ordinal()).isEqualTo(27);
        assertThat(SubjectCategory.THEATER.ordinal()).isEqualTo(28);
        assertThat(SubjectCategory.FOREIGN_LANGUAGE.ordinal()).isEqualTo(29);
        assertThat(SubjectCategory.ARTIFICIAL_INTELLIGENCE.ordinal()).isEqualTo(30);
        assertThat(SubjectCategory.ROBOTICS.ordinal()).isEqualTo(31);
        assertThat(SubjectCategory.GRAPHIC_DESIGN.ordinal()).isEqualTo(32);
        assertThat(SubjectCategory.JOURNALISM.ordinal()).isEqualTo(33);
        assertThat(SubjectCategory.ARCHITECTURE.ordinal()).isEqualTo(34);
    }
}

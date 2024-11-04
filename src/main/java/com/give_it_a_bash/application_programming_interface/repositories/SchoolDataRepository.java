/*
 * Copyright (c) 2024 Give It A Bash
 *
 * This file is part of Give It A Bash proprietary software.
 * Unauthorized copying of this file, via any medium, is strictly prohibited.
 * Proprietary and confidential.
 *
 * Created and maintained by Give It A Bash.
 */

package com.give_it_a_bash.application_programming_interface.repositories;

import com.give_it_a_bash.application_programming_interface.entities.SchoolData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing SchoolData entities in the database.
 * This interface extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface SchoolDataRepository extends JpaRepository<SchoolData, Long> {
}

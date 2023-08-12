package com.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberEntryRepository extends JpaRepository<NumberEntry, Long> {
    // Additional query methods if needed
}

package com.example;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class NumberEntryService{
    private final JdbcTemplate jdbcTemplate;

    public NumberEntryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void saveNumbers(int number1, int number2, int number3) {
        String insertQuery = "INSERT INTO number_entry (value) VALUES (?)";

        jdbcTemplate.update(insertQuery, number1);
        jdbcTemplate.update(insertQuery, number2);
        jdbcTemplate.update(insertQuery, number3);
    }
}
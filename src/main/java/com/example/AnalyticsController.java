package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
public class AnalyticsController {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public AnalyticsController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/calculate-analytics")
    public String calculateAndSaveAnalytics(Model model) {

        List<Integer> numbers =getAllAnalyticsData();
        if (!numbers.isEmpty()) {

            int min = Collections.min(numbers);
            int max = Collections.max(numbers);
            double average = numbers.stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);
            AnalyticsDataService analyticsDataService = new AnalyticsDataService();
            analyticsDataService.saveAnalyticsData(average,(double)min,(double)max);

            model.addAttribute("message", "Analytics data calculated and saved.");
        } else {
            model.addAttribute("message", "No data available for analytics.");
        }

        return "analytics-result"; // Return the name of the Thymeleaf template
    }

    public List<Integer> getAllAnalyticsData() {
        List<Integer> numbers = new ArrayList<>();
        try {
            String selectQuery = "SELECT value FROM number_entry";
            numbers = jdbcTemplate.queryForList(selectQuery, Integer.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return numbers;
    }
}

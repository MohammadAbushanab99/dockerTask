package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyticsController {
    private final NumberEntryRepository numberEntryRepository;
    private final AnalyticsDataService analyticsDataService;

    public AnalyticsController(NumberEntryRepository numberEntryRepository, AnalyticsDataService analyticsDataService) {
        this.numberEntryRepository = numberEntryRepository;
        this.analyticsDataService = analyticsDataService;
    }

    @GetMapping("/calculate-analytics")
    public String calculateAndSaveAnalytics(Model model) {
        List<NumberEntry> numberEntries = numberEntryRepository.findAll();

        if (!numberEntries.isEmpty()) {
            List<Double> numbers = extractNumbersFromEntries(numberEntries);
            double average = calculateAverage(numbers);
            double min = Collections.min(numbers);
            double max = Collections.max(numbers);
            analyticsDataService.saveAnalyticsData(average, min, max);

            model.addAttribute("message", "Analytics data calculated and saved.");
        } else {
            model.addAttribute("message", "No data available for analytics.");
        }

        return "analytics-result"; // Return the name of the Thymeleaf template
    }

    private List<Double> extractNumbersFromEntries(List<NumberEntry> numberEntries) {
        return numberEntries.stream()
                .map(NumberEntry::getValue)
                .collect(Collectors.toList());
    }

    private double calculateAverage(List<Double> numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.size();
    }
}

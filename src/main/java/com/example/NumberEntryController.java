package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NumberEntryController {
    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public NumberEntryController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/dashboard")
    public String showNumberEntryForm() {
        return "dashboard";
    }

    @PostMapping("/dashboard")
    public String processNumberEntry(
            @RequestParam String number1,
            @RequestParam String number2,
            @RequestParam String number3,
            RedirectAttributes redirectAttributes
    ) {

        System.out.println("insertData");
        String insertQuery = "INSERT INTO number_entry (value) VALUES (?)";

        jdbcTemplate.update(insertQuery, Integer.parseInt(number1));
        jdbcTemplate.update(insertQuery, Integer.parseInt(number2));
        jdbcTemplate.update(insertQuery, Integer.parseInt(number3));

        redirectAttributes.addFlashAttribute("success", "Numbers saved successfully.");
        return "redirect:/dashboard";
    }
}

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
    private final NumberEntryService numberEntryService;

    public NumberEntryController(NumberEntryService numberEntryService) {
        this.numberEntryService = numberEntryService;
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

        numberEntryService.saveNumbers(Integer.parseInt(number1), Integer.parseInt(number2), Integer.parseInt(number3));

        redirectAttributes.addFlashAttribute("success", "Numbers saved successfully.");
        return "redirect:/dashboard";
    }
}

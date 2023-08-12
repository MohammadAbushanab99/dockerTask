package com.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

//    @GetMapping("/enter-numbers")
//    public String showNumberEntryForm() {
//        return "number-entry-form";
//    }

    @PostMapping("/enter-numbers")
    public String processNumberEntry(
            @RequestParam double number1,
            @RequestParam double number2,
            @RequestParam double number3,
            RedirectAttributes redirectAttributes
    ) {

        numberEntryService.saveNumberEntry(number1);
        numberEntryService.saveNumberEntry(number2);
        numberEntryService.saveNumberEntry(number3);

        redirectAttributes.addFlashAttribute("success", "Numbers saved successfully.");
        return "redirect:/dashboard";
    }
}

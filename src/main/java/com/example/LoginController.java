package com.example;

import com.example.AnalyticsDataService;
import com.example.AuthService;
import com.example.NumberEntryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private final AuthService authService;
    private final NumberEntryService numberEntryService;
    private final AnalyticsDataService analyticsDataService;

    public LoginController(AuthService authService, NumberEntryService numberEntryService, AnalyticsDataService analyticsDataService) {
        this.authService = authService;
        this.numberEntryService = numberEntryService;
        this.analyticsDataService = analyticsDataService;
    }


    @PostMapping("/login")
    public String processLogin(@RequestParam String username, @RequestParam String password, RedirectAttributes redirectAttributes) {
        if (authService.authenticate(username, password)) {
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "Invalid credentials. Please try again.");
            return "redirect:/login";
        }
    }
}

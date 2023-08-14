package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AnalyticsController {
    @Autowired
    private AnalyticsDataService analyticsDataService;
    private GetAnaltyicsDataService getAnaltyicsDataService;

    public AnalyticsController(AnalyticsDataService analyticsDataService, GetAnaltyicsDataService getAnaltyicsDataService) {
        this.analyticsDataService = analyticsDataService;
        this.getAnaltyicsDataService = getAnaltyicsDataService;
    }

    @PostMapping("/calculate-analytics")
    public String calculateAndSaveAnalytics(Model model) {

        analyticsDataService.saveAnalyticsData();
        return "dashboard";
    }
    @GetMapping("/getResult")
    public String result(Model model){

        model.addAttribute("average", getAnaltyicsDataService.getLastRecord().getAverage());
        model.addAttribute("min", getAnaltyicsDataService.getLastRecord().getMin());
        model.addAttribute("max", getAnaltyicsDataService.getLastRecord().getMax());
        return "dashboard";
    }



}

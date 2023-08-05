package com.example.demo.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {


    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError() {
        // Custom error handling logic here
        // Return the name of your error view template (e.g., "error.html")
        return "error";
    }
}
package com.example.demo.controllers;

import com.example.demo.DemoApplication;
import com.example.demo.data.User;
import com.example.demo.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final User userDao;
    ModelAndView modelAndView = new ModelAndView();
    @Autowired
    public LoginController(User userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm() {


        modelAndView.setViewName("login");
        return modelAndView;

    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("id") String id,
                               @RequestParam("password") String password,
                               Model model,
                               HttpSession session) {

        String userType = userDao.checkUserInput(id, password);
        System.out.println(userType);
        if (userType != null && !userType.isEmpty()) {

            switch (userType) {
                case "Instructor":
                    session.setAttribute("id", id);
                    return  "redirect:/login/instructor";
                case "admin":
                    session.setAttribute("id", id);
                   return  "redirect:/login/admin";
                case "Student":
                    return "student";
                default:
                    model.addAttribute("errorMessage", "Invalid User Type!");
                    return "login";
            }
        } else {
            model.addAttribute("errorMessage", "Invalid Credentials!!");
            return "login";
        }
    }


}



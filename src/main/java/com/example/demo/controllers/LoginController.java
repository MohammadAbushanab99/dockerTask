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

@Controller
public class LoginController {
    private final User userDao;

    @Autowired
    public LoginController(User userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login1"); // Use the .jsp extension
        return modelAndView;

    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("id") String id,
                               @RequestParam("password") String password,
                               Model model) {

        String userType = userDao.checkUserInput(id, password);
        System.out.println(userType);
        if (userType != null && !userType.isEmpty()) {

            model.addAttribute("errorMessage", "hi");
            return "login";
//            switch (userType) {
//                case "Instructor":
//                    return "redirect:/instructor";
//                case "admin":
//                    return "redirect:/admin";
//                case "Student":
//                    return "redirect:/student";
//                default:
//                    model.addAttribute("errorMessage", "Invalid User Type!");
//                    return "html1";
//            }
        } else {
            model.addAttribute("errorMessage", "Invalid Credentials!!");
            return "login";
        }
    }
}

//@Controller
//public static class LoginController {
//
//    //    private final UserDao userDao;
//    //    public LoginController(UserDao userDao) {
//    //        this.userDao = userDao;
//    //    }
//
//
//    @GetMapping("/login")
//    public String showLoginForm() {
//        return "login"; //
//    }
//
//
//    //    @PostMapping("/login")
//    //    public String processLogin(@RequestParam("id") String id,
//    //                               @RequestParam("password") String password,
//    //                               Model model) {
//    //
//    //        UserDao userDao = null;
//    //        String userType = userDao.checkUserInput(id, password);
//    //        if (userType != null && !userType.isEmpty()) {
//    //
//    //            switch (userType) {
//    //                case "Instructor":
//    //                    return "redirect:/instructor";
//    //                case "admin":
//    //                    return "redirect:/admin";
//    //                case "Student":
//    //                    return "redirect:/student";
//    //                default:
//    //                    model.addAttribute("errorMessage", "Invalid User Type!");
//    //                    return "login";
//    //            }
//    //        } else {
//    //            model.addAttribute("errorMessage", "Invalid Credentials!!");
//    //            return "login";
//    //        }
//    //    }
//}

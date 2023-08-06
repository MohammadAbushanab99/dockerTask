package com.example.demo.controllers;

import com.example.demo.data.*;
import com.example.demo.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("id")
public class AdminController {

     private final AdminDao adminDao;
    private final UserDao userDao;
    private final CourseDb courseDao;
    @Autowired
    public AdminController(Admin adminDAO, User userDao, CourseDb courseDao) {
        this.adminDao = adminDAO;
        this.userDao = userDao;
        this.courseDao = courseDao;
    }

    @GetMapping("/login/admin")
    public String showAdminPage(Model model, HttpSession session) {
        String id = (String) session.getAttribute("id");


        model.addAttribute("id", id);

             List<Course> courses = courseDao.getCoursesInformation();
            model.addAttribute("courseList", courses);

        Map<String, String> instructors = userDao.getInstructor();
        model.addAttribute("InstructorList", instructors);
        return "admin";
    }
//    @GetMapping("/admin")
//    public ModelAndView adminInterface(Model model, HttpSession session) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin");
//        System.out.println("Admin endpoint accessed!");
//
//        return modelAndView;
////        Map<String, String> instructors = userDao.getInstructor();
////        String id = (String) session.getAttribute("id");
////        System.out.println("hello"+id );
////
////
////        String password = (String) session.getAttribute("password");
////
////        if (id != null && !id.isEmpty()) {
////            model.addAttribute("id", id);
////        }
////
////        model.addAttribute("InstructorList", instructors);
////
//////        List<Course> courses = courseDao.getCoursesInformation();
//////        model.addAttribute("courseList", courses);
////
////        return "Admin";
//
//    }
//
    @PostMapping("/login/admin/addInstructors")
    public String addInstructor(@RequestParam("InstructorName") String instructorName,Model model) {
        Map<String, String> instructors = userDao.getInstructor();
        System.out.println("abushanb");
        if (instructorName != null && !instructorName.isEmpty()) {
            if (userDao.checkIfUserInputExist("instructors", instructorName)) {
                model.addAttribute("addInstructor", "name is already exist");
            } else {
                adminDao.insertNewInstructor(instructorName);
                model.addAttribute("addInstructor", "added successfully");
            }
        } else {
            model.addAttribute("addInstructor", "name is required");
        }

        return "admin";
    }
//
//    @PostMapping("/Admin/addCourse")
//    public String addCourse(@RequestParam("CourseId") String courseName,
//                            @RequestParam("InstructorIdCourse") String instructorIdCourse) {
//        Map<String, String> instructors = userDao.getInstructor();
//
//        if (courseName != null && !courseName.isEmpty()) {
//            if (instructorIdCourse != null && !instructorIdCourse.isEmpty()) {
//                if (instructors.containsKey(instructorIdCourse)) {
//                    adminDao.insertNewCourse(courseName, instructorIdCourse);
//                }
//            }
//        }
//
//        return "redirect:/Admin";
//    }
//
//    @PostMapping("/Admin/addStudent")
//    public String addStudent(@RequestParam("StudentName") String studentName,
//                             @RequestParam("StudentMajor") String studentMajor,
//                             @RequestParam("selectedCourseId") int selectedCourseId) {
//        List<Course> courses = courseDao.getCoursesInformation();
//
//        if (studentName != null && !studentName.isEmpty()) {
//            if (studentMajor != null && !studentMajor.isEmpty()) {
//                String studentId;
//                if (!userDao.checkIfUserInputExist("students", studentName)) {
//                    studentId = adminDao.insertNewStudent(studentName, studentMajor);
//                } else {
//                    studentId = userDao.getUserId("students", studentName);
//                }
//                Course selectedCourse = getCourseById(selectedCourseId, courses);
//                adminDao.addStudentToCourse(studentId, selectedCourse);
//            }
//        }
//
//        return "redirect:/Admin";
//    }
//
//    private Course getCourseById(int courseId, List<Course> courses) {
//        for (Course c : courses) {
//            if (c.getId() == courseId) {
//                return c;
//            }
//        }
//        return courses.get(0);
//    }
}

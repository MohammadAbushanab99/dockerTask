package com.example.demo.controllers;

import com.example.demo.data.AdminDao;
import com.example.demo.data.CourseDao;
import com.example.demo.data.UserDao;
import com.example.demo.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class AdminController {

     private final AdminDao adminDao;
    private final UserDao userDao;
    private final CourseDao courseDao;
    @Autowired
    public AdminController(AdminDao adminDAO, UserDao userDao, CourseDao courseDao) {
        this.adminDao = adminDAO;
        this.userDao = userDao;
        this.courseDao = courseDao;
    }

    @GetMapping("/Admin")
    public String adminInterface(Model model, HttpSession session) {


        Map<String, String> instructors = userDao.getInstructor();
        String id = (String) session.getAttribute("id");
        String password = (String) session.getAttribute("password");

        if (id != null && !id.isEmpty()) {
            model.addAttribute("Admin", id);
        }

        model.addAttribute("InstructorList", instructors);

        List<Course> courses = courseDao.getCoursesInformation();
        model.addAttribute("courseList", courses);

        return "Admin";
    }

    @PostMapping("/Admin/addInstructor")
    public String addInstructor(@RequestParam("InstructorName") String instructorName) {
        Map<String, String> instructors = userDao.getInstructor();

        if (instructorName != null && !instructorName.isEmpty()) {
            if (!userDao.checkIfUserInputExist("instructors", instructorName)) {
                adminDao.insertNewInstructor(instructorName);
            }
        }

        return "redirect:/Admin";
    }

    @PostMapping("/Admin/addCourse")
    public String addCourse(@RequestParam("CourseId") String courseName,
                            @RequestParam("InstructorIdCourse") String instructorIdCourse) {
        Map<String, String> instructors = userDao.getInstructor();

        if (courseName != null && !courseName.isEmpty()) {
            if (instructorIdCourse != null && !instructorIdCourse.isEmpty()) {
                if (instructors.containsKey(instructorIdCourse)) {
                    adminDao.insertNewCourse(courseName, instructorIdCourse);
                }
            }
        }

        return "redirect:/Admin";
    }

    @PostMapping("/Admin/addStudent")
    public String addStudent(@RequestParam("StudentName") String studentName,
                             @RequestParam("StudentMajor") String studentMajor,
                             @RequestParam("selectedCourseId") int selectedCourseId) {
        List<Course> courses = courseDao.getCoursesInformation();

        if (studentName != null && !studentName.isEmpty()) {
            if (studentMajor != null && !studentMajor.isEmpty()) {
                String studentId;
                if (!userDao.checkIfUserInputExist("students", studentName)) {
                    studentId = adminDao.insertNewStudent(studentName, studentMajor);
                } else {
                    studentId = userDao.getUserId("students", studentName);
                }
                Course selectedCourse = getCourseById(selectedCourseId, courses);
                adminDao.addStudentToCourse(studentId, selectedCourse);
            }
        }

        return "redirect:/Admin";
    }

    private Course getCourseById(int courseId, List<Course> courses) {
        for (Course c : courses) {
            if (c.getId() == courseId) {
                return c;
            }
        }
        return courses.get(0);
    }
}

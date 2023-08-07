package com.example.demo.controllers;

import com.example.demo.data.*;
import com.example.demo.models.Course;
import com.example.demo.models.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@SessionAttributes("id")
public class InstructorController {

    private final InstructorDao instructorDao ;
    private final UserDao userDao;
    private final CourseDb courseDao;

    @Autowired
    public InstructorController(Instructor instructorDao, User userDao, CourseDb courseDao) {
        this.instructorDao = instructorDao;
        this.userDao = userDao;
        this.courseDao = courseDao;
    }

    @GetMapping("/login/instructor")
    public String showInstructorInterface( Model model,HttpSession session ) {


        String id = (String) session.getAttribute("id");

       String name = userDao.getInstructorName(id);
//
       if (id != null && !id.isEmpty()) {
            model.addAttribute("instructorId", id);
            model.addAttribute("instructorName", name);
        }
//
        List<Course> courses = courseDao.getCoursesInformation(id);
        model.addAttribute("courseList", courses);
//
//        //String selectedCourseIdParam = request.getParameter("selectedCourseId");
//


//        if (selectedCourseId != null && !selectedCourseId.isEmpty()) {
//            try {
//
//                selectedCourse = getCourseById(Integer.parseInt(selectedCourseId), courses);
//                List<Grade> students = instructorDao.getStudentsInformation(selectedCourse, selectedCourse.getInstructorId());
//                model.addAttribute("selectedCourseId", Integer.parseInt(selectedCourseId));
//                model.addAttribute("selectedCourseName", selectedCourse.getCourseName());
//
//                if (selectedCourse.getExamType() == 1) {
//                    model.addAttribute("remarks1", "Mid Exam");
//                    model.addAttribute("remarks2", "Quizzes");
//                } else {
//                    model.addAttribute("remarks1", "First Exam");
//                    model.addAttribute("remarks2", "Second Exam");
//                }
//
//                model.addAttribute("studentsList", students);
//                model.addAttribute("selectedCourse", selectedCourse);
//            } catch (NumberFormatException e) {
//                model.addAttribute("error", "error in input");
//            }
//        }

        return "instructor";
    }

    @PostMapping("/login/instructor/addGrades")
    public String addGrades(@RequestParam("selectedCourseId") String selectedCourseId,Model model,HttpSession session){
        String id = (String) session.getAttribute("id");
        List<Course> courses = courseDao.getCoursesInformation(id);
        Course selectedCourse = null;
        if (selectedCourseId != null && !selectedCourseId.isEmpty()) {
            try {
                System.out.println("1");
                selectedCourse = getCourseById(Integer.parseInt(selectedCourseId), courses);
                List<Grade> students = instructorDao.getStudentsInformation(selectedCourse, selectedCourse.getInstructorId());
                System.out.println(students.isEmpty());
                model.addAttribute("selectedCourseId", Integer.parseInt(selectedCourseId));
                model.addAttribute("selectedCourseName", selectedCourse.getCourseName());

                if (selectedCourse.getExamType() == 1) {
                    model.addAttribute("remarks1", "Mid Exam");
                    model.addAttribute("remarks2", "Quizzes");
                } else {
                    model.addAttribute("remarks1", "First Exam");
                    model.addAttribute("remarks2", "Second Exam");
                }

                System.out.println("2");

                model.addAttribute("studentsList", students);
               // model.addAttribute("selectedCourse", selectedCourse);
            } catch (NumberFormatException e) {
                model.addAttribute("error", "error in input");
            }
        }


        return "instructor";
    }

//    @PostMapping("/login/instructor/saveRemarks")
//    @ResponseBody
//    public String saveRemarks(@RequestParam("selectedCourseId") String selectedCourseId,
//                              @RequestBody Grade remarksData) {
//        // Now you have the data in 'remarksData', you can save it in the database
//        // ... (Implement the database saving logic here)
//
//        return "{\"success\": true}";
//    }

    private Course getCourseById(int courseId, List<Course> courses) {
        for (Course c : courses) {
            if (c.getId() == courseId) {
                return c;
            }
        }
        return courses.get(0);
    }


    public class RemarksData {
        private String studentId;
        private String remarks1;
        private String remarks2;
        private String finalGrade;
        private String totalGrade;

        public String getStudentId() {
            return studentId;
        }

        public String getRemarks1() {
            return remarks1;
        }

        public String getRemarks2() {
            return remarks2;
        }

        public String getFinalGrade() {
            return finalGrade;
        }

        public String getTotalGrade() {
            return totalGrade;
        }
    }
}

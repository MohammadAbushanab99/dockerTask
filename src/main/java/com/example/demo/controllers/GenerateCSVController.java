package com.example.demo.controllers;

import com.example.demo.data.CourseDao;
import com.example.demo.data.InstructorDao;
import com.example.demo.data.UserDao;
import com.example.demo.models.Course;
import com.example.demo.models.Grade;
import com.example.demo.models.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GenerateCSVController {

    private final CourseDao courseDao;
    private final InstructorDao instructorDao;
    private final UserDao userDao;
    @Autowired
    public GenerateCSVController(CourseDao courseDao, InstructorDao instructorDao,UserDao userDao) {
        this.courseDao = courseDao;
        this.instructorDao = instructorDao;
        this.userDao = userDao;
    }

    @GetMapping("/GenerateCSV")
    public String generateCSV(HttpServletRequest request) {
        Writer writer = new Writer();
        int selectedId = Integer.parseInt(request.getParameter("courseIdCSV"));
        String id = request.getParameter("myId");
        if (!id.isEmpty()) {
            List<Course> instructorCourse = courseDao.getCoursesInformation(id);
            Course course = chooseCourse(selectedId, instructorCourse);
            List<Grade> students1 = instructorDao.getStudentsInformation(course, id);
            if (!students1.isEmpty()) {
                writer.writeToFile(students1,userDao);
            }
        }

        return "instructor";
    }

    private Course chooseCourse(int courseId, List<Course> courseList) {


            Course tmpCourse = null;
            for (Course course1 : courseList) {
                if (courseId == course1.getId()) {
                    tmpCourse = course1;
                    return tmpCourse;
                }
            }
            return tmpCourse;

    }
}
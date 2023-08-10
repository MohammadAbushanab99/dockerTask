package com.example.demo.controllers;

import com.example.demo.data.*;
import com.example.demo.models.Grades;
import com.example.demo.models.StudentGrade2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("id")
public class StudentController {


    private final UserDao userDao;
    private final CourseDb courseDao;

    @Autowired
    public StudentController( User userDao, CourseDb courseDao) {
        this.userDao = userDao;
        this.courseDao = courseDao;
    }


    @GetMapping("/login/student")
    public String showStudentInterface(Model model, HttpSession session ) {
        Map <String,String> courses = new HashMap<>();
        List<StudentGrade2> gradesInfo = new ArrayList<>();

        String id = (String) session.getAttribute("id");
        String name = userDao.getStudentName(id);

        if (id != null && !id.isEmpty()) {
            model.addAttribute("StudentId", id);
            model.addAttribute("StudentName", name);


        List<Grades> grades = userDao.getStudentsInformation(id);

        for(Grades info :grades){
            String insructorName = userDao.getInstructorName(info.getInstructorId());

            String courseName = courseDao.getCourseName(info.getCourseId());
            courses.put(courseName,insructorName);

            gradesInfo.add(new StudentGrade2(insructorName,info.getFirstExam(),info.getSecondExam(),
                                            info.getFinalExam(), info.getMidExam(), info.getQuizzes(),
                                            info.getTotalGrade(),info.getCourseId(),courseName));

        }

        model.addAttribute("courseMap", courses);
       model.addAttribute("gradesInfo",gradesInfo);

        }


        return "student";
    }
}

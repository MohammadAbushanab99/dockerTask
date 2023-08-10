package com.example.demo.controllers;

import com.example.demo.data.InstructorDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Controller
public class SaveMarksController {
    private final InstructorDao instructorDao;


    public SaveMarksController(InstructorDao instructorDao) {
        this.instructorDao = instructorDao;
    }

    @PostMapping("/login/instructor/SaveMarks")
    public String saveMarks(@RequestBody Grades2 studentGrade , HttpServletResponse response) throws IOException {

        instructorDao.addGradesForStudents(studentGrade);
        boolean success = true;

        if(!studentGrade.getStudentId().isEmpty()) {
            String jsonResponse = "{\"success\":" + success + "}";

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
        }


        return "instructor";
    }

    public static class Grades2 {

        private String selectedStudentId;
        private int midExam;
        private int quizzes;
        private int firstExam;
        private int secondExam;
        private int finalExam;
        private int totalGrade;
        private int courseId;
        private String id;

        public Grades2(String selectedStudentId, int midExam, int quizzes, int firstExam, int secondExam, int finalExam, int totalGrade , int courseId,String id) {
            this.id = id;
            this.firstExam = firstExam;
            this.secondExam = secondExam;
            this.finalExam = finalExam;
            this.midExam = midExam;
            this.quizzes = quizzes;
            this.totalGrade = totalGrade;
            this.courseId = courseId;
            this.selectedStudentId = selectedStudentId;
        }

        public String getInstructorId() {
            return id;
        }

        public int getCourseId() {
            return courseId;
        }

        public int getFirstExam() {
            return firstExam;
        }

        public int getSecondExam() {
            return secondExam;
        }

        public int getFinalExam() {
            return finalExam;
        }

        public int getMidExam() {
            return midExam;
        }

        public int getQuizzes() {
            return quizzes;
        }

        public int getTotalGrade() {
            return totalGrade;
        }

        public String getStudentId() {
            return selectedStudentId;
        }
    }




}

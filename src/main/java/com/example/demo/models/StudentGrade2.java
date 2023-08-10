package com.example.demo.models;

public class StudentGrade2 {
    private String instructorName;
    private int courseId;
    private int firstExam;
    private int secondExam;
    private int finalExam;
    private int midExam;
    private int quizzes;
    private int totalGrade;
    private String courseName;


    public StudentGrade2(String instructorName, int firstExam, int secondExam, int finalExam, int midExam, int quizzes, int totalGrade, int courseId, String courseName) {
        this.instructorName = instructorName;
        this.firstExam = firstExam;
        this.secondExam = secondExam;
        this.finalExam = finalExam;
        this.midExam = midExam;
        this.quizzes = quizzes;
        this.totalGrade = totalGrade;
        this.courseId = courseId;
        this.courseName = courseName;
    }


    public String getInstructorName() {
        return instructorName;
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

    public String getCourseName() {
        return courseName;
    }
}

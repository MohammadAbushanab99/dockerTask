package com.example.demo.models;

public class Grades {



    private String instructorId;
    private int firstExam;
    private int secondExam;
    private int finalExam;
    private int midExam;
    private int quizzes;
    private int totalGrade;
    private int courseId;

    public Grades(String instructorId, int firstExam, int secondExam, int finalExam, int midExam, int quizzes, int totalGrade , int courseId) {
        this.instructorId = instructorId;
        this.firstExam = firstExam;
        this.secondExam = secondExam;
        this.finalExam = finalExam;
        this.midExam = midExam;
        this.quizzes = quizzes;
        this.totalGrade = totalGrade;
        this.courseId = courseId;
    }

    public String getInstructorId() {
        return instructorId;
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
}

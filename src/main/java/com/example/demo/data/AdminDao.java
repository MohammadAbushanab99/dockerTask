package com.example.demo.data;

import com.example.demo.models.Course;

public interface AdminDao {

    public void addStudentToCourse(String id, Course course);

    public void updateNumberOfStudents(Course course);

    public void insertNewCourse(String courseName, String instructorId);

    public void insertNewInstructor(String name);

    public String insertNewStudent(String name, String major);
}

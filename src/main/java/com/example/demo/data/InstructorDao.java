package com.example.demo.data;

import com.example.demo.models.Course;
import com.example.demo.models.Grade;
import com.example.demo.models.StudentCourse;
import com.example.demo.models.StudentGrade;

import java.util.List;

public interface InstructorDao {

    public String getExamType(int id);

    public List<Grade> getStudentsInformation(Course course, String instructorId);

    public void addCourseCriteria(String gradeType, String examType, Course course);

    public void addGradesForStudents(Course course, Grade grade);

    public void getStudentsInformation(String studentId, List<StudentGrade> studentGrades, List<StudentCourse> studentCourses);
}

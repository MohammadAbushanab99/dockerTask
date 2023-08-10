package com.example.demo.data;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("database")
public class CourseDb implements CourseDao {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDb(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public  List<Course> getCoursesInformation() {
        List<Course> courses = new ArrayList<>();
        try {
            String query = "SELECT id, course_name, instructor_id, number_of_students, exam_type_id, grad_type_id FROM courses";
            courses = jdbcTemplate.query(query, (rs, rowNum) -> {
                int courseId = rs.getInt("id");
                String courseName = rs.getString("course_name");
                String instructorId = rs.getString("instructor_id");
                int numbersOfStudents = rs.getInt("number_of_students");
                int examType = rs.getInt("exam_type_id");
                int gradeType = rs.getInt("grad_type_id");

                return new Course(courseId, courseName, numbersOfStudents, examType, gradeType, instructorId);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }
    @Override
    public List<Course> getCoursesInformation(String id) {
        List<Course> courses = new ArrayList<>();
        try {
            String query = "SELECT id, course_name, instructor_id, number_of_students, exam_type_id, grad_type_id FROM courses WHERE instructor_id = ?";
            courses = jdbcTemplate.query(query, (rs, rowNum)  -> {
                int courseId = rs.getInt("id");
                String courseName = rs.getString("course_name");
                String instructorId = rs.getString("instructor_id");
                int numbersOfStudents = rs.getInt("number_of_students");
                int examType = rs.getInt("exam_type_id");
                int gradeType = rs.getInt("grad_type_id");

                return new Course(courseId, courseName, numbersOfStudents, examType, gradeType, instructorId);
            } ,id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public String getCourseName(int id) {
        String name = "";
        try {
            String query = "SELECT course_name FROM courses WHERE id = ?";
            name = jdbcTemplate.queryForObject(query, String.class, id);;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
}

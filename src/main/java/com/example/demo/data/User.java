package com.example.demo.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("database")
public class User implements UserDao {

        private final JdbcTemplate jdbcTemplate;

        @Autowired
        public User(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
    public String checkUserInput(String id, String password) {
        String userType = "";
        try {
            String query = "SELECT user_type_id FROM USERS WHERE user_id = ? AND password = ?";
            int userTypeId = jdbcTemplate.queryForObject(query, Integer.class, id, password);
            query = "SELECT type_name FROM USER_TYPES WHERE id = ?";

            userType = jdbcTemplate.queryForObject(query, String.class, userTypeId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userType;
    }
    @Override
    public Map<String, String> getInstructor() {
        Map<String, String> instructorNames = new HashMap<>();
        try {
            String query = "SELECT name, instructor_id FROM instructors";
            jdbcTemplate.query(query, (rs, rowNum) -> {
                String name = rs.getString("name");
                String id = rs.getString("instructor_id");
                instructorNames.put(id, name);
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instructorNames;
    }

    @Override
    public String getUserId(String userType, String userName) {
        String id = "";
        try {
            String query = "SELECT id FROM " + userType + " WHERE name = ?";
            id = jdbcTemplate.queryForObject(query, (ResultSet rs, int rowNum) -> rs.getString(1), String.class, userType);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public boolean checkIfUserInputExist(String userType, String userName) {
        try {
            String query = "SELECT name FROM " + userType + " WHERE name = ?";
            return Boolean.TRUE.equals(jdbcTemplate.queryForObject(query, (ResultSet rs, int rowNum) -> {
                String name = rs.getString(1);
                return name != null;
            }, userName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public String getStudentName(String id) {
        String name = null;
        try {
            String query = "SELECT name FROM students WHERE student_id = ?";
            name = jdbcTemplate.queryForObject(query, String.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
    @Override
    public String getInstructorName(String id) {
        String name = "";
        try {
            String query = "SELECT name FROM instructors WHERE instructor_id = ?";
            name = jdbcTemplate.queryForObject(query, String.class, id);;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public List<Grades> getStudentsInformation(String id) {
        List<Grades> studentGrades = new ArrayList<>();
        try {
            String query = "SELECT id_course,instructor_id, first_exam, second_exam, final_exam, mid_exam, quizzes, total_grade " +
                    "FROM grade WHERE student_id = ?";
            studentGrades = jdbcTemplate.query(query, (rs, rowNum) -> {

                 String instructorId = rs.getString("instructor_id");
                int firstExam = rs.getInt("first_exam") != 0 ? rs.getInt("first_exam") : 0;
                    int secondExam = rs.getInt("second_exam")!= 0 ? rs.getInt("second_exam") : 0;
                    int finalExam = rs.getInt("final_exam") != 0 ? rs.getInt("final_exam") : 0;
                    int midExam = rs.getInt("mid_exam")!= 0 ? rs.getInt("mid_exam") : 0;
                    int quizzes = rs.getInt("quizzes")!= 0 ? rs.getInt("quizzes") : 0;
                    int totalGrade = rs.getInt("total_grade")!= 0 ? rs.getInt("quizzes") : 0;
                    int courseId = rs.getInt("id_course");


                return new Grades(instructorId, firstExam, secondExam, finalExam, midExam, quizzes, totalGrade,courseId);
            }, id);
        } catch (Exception e) {
            e.printStackTrace();
        }



        return studentGrades ;
    }

}












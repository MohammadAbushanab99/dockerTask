package com.example.demo.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

}












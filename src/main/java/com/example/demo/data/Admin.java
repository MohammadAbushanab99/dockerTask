package com.example.demo.data;

import com.example.demo.models.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("database")
public class Admin implements AdminDao{


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Admin(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

     @Override
    public void addStudentToCourse(String id, Course course) {
        try {
            String query = "INSERT INTO grade (student_id, id_course, instructor_id) VALUES (?, ?, ?)";
            int rowsAffected = jdbcTemplate.update(query, id, course.getId(), course.getInstructorId());

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
            updateNumberOfStudents(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateNumberOfStudents(Course course) {
        try {
            String query = "UPDATE COURSES SET number_of_students = ? WHERE id = ? AND instructor_id = ?";
            int rowsAffected = jdbcTemplate.update(query, course.getNumberOfStudents() + 1, course.getId(), course.getInstructorId());

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insertNewCourse(String courseName, String instructorId) {
        try {
            String query = "INSERT INTO courses (course_name, instructor_id) VALUES (?, ?)";
            int rowsAffected = jdbcTemplate.update(query, courseName, instructorId);

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insertNewInstructor(String name) {
        String id = generateAccount("Instructor");
        System.out.println("insert22222222");
        try {
            String query = "INSERT INTO INSTRUCTORS (instructor_id, name) VALUES (?, ?)";
            int rowsAffected = jdbcTemplate.update(query, id, name);

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String insertNewStudent(String name, String major) {
        String id = generateAccount("Student");
        try {
            String query = "INSERT INTO STUDENTS (student_id, name, major) VALUES (?, ?, ?)";
            int rowsAffected = jdbcTemplate.update(query, id, name, major);

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    private String generateAccount(String userType) {
        String id = getLastUserId(userType);
        int userTypeId = checkUserTypeId(userType);
        try {
            String query = "INSERT INTO USERS (user_id, password, user_type_id) VALUES (?, ?, ?)";
            int rowsAffected = jdbcTemplate.update(query, id, "123", userTypeId);

            if (rowsAffected > 0) {
                System.out.println("Data inserted successfully.");
            } else {
                System.out.println("Failed to insert data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }



    public String getLastUserId(String userType) {
        int userTypeId = checkUserTypeId(userType);
        String lastId = null;

        try {
            String query = "SELECT id, user_id\n" +
                    "FROM users\n" +
                    "WHERE user_type_id = ?\n" +
                    "AND id = (SELECT MAX(id) FROM users WHERE user_type_id = ? AND user_id = users.user_id);";
            lastId = jdbcTemplate.queryForObject(query, new Object[]{userTypeId, userTypeId}, String.class);
            if (lastId == null) {
                if (userTypeId == 2)
                    lastId = "0";
                else
                    lastId = "1";
            }
        } catch (Exception e) {
            if (userTypeId == 2)
                lastId = "0";
            else
                lastId = "1";
        }

        String newId = generateUserId(userTypeId, lastId);

        return newId;
    }

    private String generateUserId(int userTypeId, String last_id) {
        String newId = String.valueOf(last_id.charAt(0));
        if(userTypeId == 2){
            if(!last_id.equals("0")) {
                String subId = last_id.substring(1);
                int id = Integer.parseInt(subId) + 1;
                newId += String.valueOf(id);
            }else {
                newId += "1";
            }

        }else{
            if(!last_id.equals("1")) {
                String subId = last_id.substring(1);
                int id = Integer.parseInt(subId) + 1;
                newId += String.valueOf(id);
            }else {
                newId += "1";
            }

        }

        return newId;
    }

    private int checkUserTypeId(String userType) {
        if (userType.equalsIgnoreCase("Student"))
            return 2;
        else
            return 3;
    }


}

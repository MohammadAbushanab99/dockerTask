package com.example.demo.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.controllers.SaveMarksController;
import com.example.demo.models.Course;
import com.example.demo.models.Grade;
import com.example.demo.models.StudentCourse;
import com.example.demo.models.StudentGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("database")
public class Instructor implements InstructorDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public Instructor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public String getExamType(int id) {
        String name = null;
        try {
            String query = "SELECT type_name FROM exam_types WHERE id = ?";
            name = jdbcTemplate.queryForObject(query, String.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    @Override
    public List<Grade> getStudentsInformation(Course course, String instructorId) {
        List<Grade> studentGrades = new ArrayList<>();

        try {
            String query = "SELECT student_id, first_exam, second_exam, final_exam, mid_exam, quizzes, total_grade " +
                    "FROM grade WHERE id_course = ? AND instructor_id = ?";
            studentGrades = jdbcTemplate.query(query, (rs, rowNum) -> {
                String studentId = rs.getString("student_id");
                int firstExam = rs.getInt("first_exam");
                int secondExam = rs.getInt("second_exam");
                int finalExam = rs.getInt("final_exam");
                int midExam = rs.getInt("mid_exam");
                int quizzes = rs.getInt("quizzes");
                int totalGrade = rs.getInt("total_grade");

                return new Grade(studentId, firstExam, secondExam, finalExam, midExam, quizzes, totalGrade);
            }, course.getId(), instructorId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return studentGrades;
    }

    @Override
    public void addCourseCriteria(String gradeType, String examType, Course course) {
        int gradeTypeId, examTypeId;
        String query = "UPDATE COURSES SET exam_type_id = ?, grad_type_id = ? WHERE id = ? AND instructor_id = ?";

        if (gradeType.equalsIgnoreCase("Letter Grade"))
            gradeTypeId = 1;
        else
            gradeTypeId = 2;

        if (gradeType.equalsIgnoreCase("Mid/Final"))
            examTypeId = 1;
        else
            examTypeId = 2;

        try {
            int rowsAffected = jdbcTemplate.update(query, examTypeId, gradeTypeId, course.getId(), course.getInstructorId());

            if (rowsAffected > 0) {
                System.out.println("Data updated successfully.");
            } else {
                System.out.println("Failed to update data.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addGradesForStudents(SaveMarksController.Grades2 grade) {
        String examTypeColumn, firstExamColumn, secondExamColumn, finalExamColumn;
        if (grade.getMidExam() > 0) {
            firstExamColumn = "mid_exam";
            secondExamColumn = "quizzes";
            finalExamColumn = "final_exam";

            String query = "UPDATE grade SET " + firstExamColumn + " = ?, " + secondExamColumn + " = ?, " + finalExamColumn + " = ? " +
                    "WHERE student_id = ? AND id_course = ?";

            try {
                int rowsAffected = jdbcTemplate.update(query, grade.getMidExam(), grade.getQuizzes(), grade.getFinalExam(),
                        grade.getStudentId(), grade.getCourseId());

                if (rowsAffected > 0) {
                    System.out.println("Data updated successfully.");
                } else {
                    System.out.println("Failed to update data.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            firstExamColumn = "first_exam";
            secondExamColumn = "second_exam";
            finalExamColumn = "final_exam";

            String query = "UPDATE grade SET " + firstExamColumn + " = ?, " + secondExamColumn + " = ?, " + finalExamColumn + " = ? " +
                    "WHERE student_id = ? AND id_course = ?";

            try {
                int rowsAffected = jdbcTemplate.update(query, grade.getMidExam(), grade.getQuizzes(), grade.getFinalExam(),
                        grade.getStudentId(), grade.getCourseId());

                if (rowsAffected > 0) {
                    System.out.println("Data updated successfully.");
                } else {
                    System.out.println("Failed to update data.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }

    @Override
    public void getStudentsInformation(String studentId, List<StudentGrade> studentGrades, List<StudentCourse> studentCourses) {
        String query = "SELECT first_exam, second_exam, final_exam, mid_exam, quizzes, total_grade, id_course, instructor_id " +
                "FROM grade WHERE student_id = ?";

        try {
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, studentId);

            if (rows.isEmpty()) {
                System.out.println("No data found for the student.");
            } else {
                for (Map<String, Object> row : rows) {
                    String instructorId = (String) row.get("instructor_id");
                    int courseId = (int) row.get("id_course");
                    int firstExam = (int) row.get("first_exam");
                    int secondExam = (int) row.get("second_exam");
                    int finalExam = (int) row.get("final_exam");
                    int midExam = (int) row.get("mid_exam");
                    int quizzes = (int) row.get("quizzes");
                    int totalGrade = (int) row.get("total_grade");

                        StudentCourse course = getCoursesInformation(courseId, studentCourses,jdbcTemplate);

                    StudentGrade grade = new StudentGrade(instructorId, firstExam, secondExam, finalExam, midExam, quizzes,
                            totalGrade, courseId, course);
                    studentGrades.add(grade);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static StudentCourse getCoursesInformation(int courseId, List<StudentCourse> studentCourses ,JdbcTemplate jdbcTemplate) {
        String query = "SELECT id, course_name, instructor_id, exam_type_id FROM courses WHERE id = ?";

        try {
            return jdbcTemplate.queryForObject(query, (rs, rowNum) -> {
                int id = rs.getInt("id");
                int examTypeId = rs.getInt("exam_type_id");
                String courseName = rs.getString("course_name");
                String instructorId = rs.getString("instructor_id");

                return new StudentCourse(id, courseName, getInstructorName(instructorId,jdbcTemplate), examTypeId);
            }, courseId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getInstructorName(String id,JdbcTemplate jdbcTemplate ) {
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

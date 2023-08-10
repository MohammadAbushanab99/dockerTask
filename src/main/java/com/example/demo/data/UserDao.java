package com.example.demo.data;

import com.example.demo.models.*;

import java.util.List;
import java.util.Map;

public interface UserDao {


    public String checkUserInput(String id, String password);

    public String getUserId(String userType, String userName);

    public String getStudentName(String id);

    public String getInstructorName(String id);

    public Map<String, String> getInstructor();

    public boolean checkIfUserInputExist(String userType, String userName);

    public List<Grades> getStudentsInformation(String studentId);

}

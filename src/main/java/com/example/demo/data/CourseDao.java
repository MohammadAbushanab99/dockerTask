package com.example.demo.data;

import com.example.demo.models.Course;

import java.util.List;

public interface CourseDao {

    public List<Course> getCoursesInformation();

    public List<Course> getCoursesInformation(String id);


    public String getCourseName(int id);


}

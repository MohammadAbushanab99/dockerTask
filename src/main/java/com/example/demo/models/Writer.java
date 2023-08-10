package com.example.demo.models;

import com.example.demo.data.UserDao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Writer {

    public void writeToFile(List<Grade> students , UserDao userDao) {

        String desktopPath = System.getProperty("user.home") + "/Desktop";
        String csvFilePath = desktopPath + "/Grades.csv";
        List<Integer> totalGrades = new ArrayList<>();
        try {
            File csvFile = new File(csvFilePath);

            if (csvFile.createNewFile()) {
                System.out.println("CSV file created successfully at: " + csvFile.getAbsolutePath());

                try (FileWriter writer = new FileWriter(csvFile);
                     PrintWriter printWriter = new PrintWriter(writer)) {

                    printWriter.println("Student ID,First Exam,Second Exam,Final Exam,Mid Exam,Quizzes,Total Grade,Name");

                    for (Grade grade : students) {
                        String csvLine = String.format("%s,%d,%d,%d,%d,%d,%d,%s",
                                grade.getStudentId(),
                                grade.getFirstExam(),
                                grade.getSecondExam(),
                                grade.getFinalExam(),
                                grade.getMidExam(),
                                grade.getQuizzes(),
                                grade.getTotalGrade(),
                                userDao.getStudentName(grade.getStudentId()));
                        totalGrades.add(grade.getMidExam());
                        printWriter.println(csvLine);
                    }

                    if (!totalGrades.isEmpty()) {
                        double average = totalGrades.stream()
                                .mapToInt(Integer::intValue)
                                .average()
                                .getAsDouble();

                        Collections.sort(totalGrades);
                        int medianIndex = totalGrades.size() / 2;
                        int median = totalGrades.get(medianIndex);

                        int highest = Collections.max(totalGrades);
                        int lowest = Collections.min(totalGrades);

                        printWriter.println("Statistics from CSV file:");
                        printWriter.println("Average Grade:," + average);
                        printWriter.println("Median Grade:," + median);
                        printWriter.println("Highest Grade, " + highest);
                        printWriter.println("Lowest Grade," + lowest);
                    }

                }
            } else {
                System.err.println("Failed to create CSV file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


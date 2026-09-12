package src.service;

import src.model.student;
import src.model.academicRecord;
import src.model.grade;

import java.util.ArrayList;

public class reportGenerator {

    private performanceAnalyzer analyzer;

    public reportGenerator() {
        analyzer = new performanceAnalyzer();
    }

    public void generateStudentReport(
            student s,
            ArrayList<academicRecord> records) {

        System.out.println();
        System.out.println(
                "========================================"
        );
        System.out.println(
                "       STUDENT ACADEMIC REPORT"
        );
        System.out.println(
                "========================================"
        );

        System.out.println(
                "Registration No.: "
                        + s.getRegistrationNumber()
        );

        System.out.println(
                "Name: " + s.getName()
        );

        System.out.println(
                "Email: " + s.getEmail()
        );

        System.out.println(
                "Program: " + s.getProgram()
        );

        System.out.println(
                "Semester: " + s.getSemester()
        );

        System.out.println(
                "----------------------------------------"
        );

        if (records.isEmpty()) {

            System.out.println(
                    "No academic records available."
            );

        } else {

            System.out.println(
                    "ACADEMIC PERFORMANCE"
            );

            System.out.println(
                    "----------------------------------------"
            );

            for (academicRecord record : records) {

                double marks = record.getMarks();

                System.out.println(
                        "Subject Code: "
                                + record.getSubject()
                                .getSubjectCode()
                );

                System.out.println(
                        "Subject: "
                                + record.getSubject()
                                .getSubjectName()
                );

                System.out.println(
                        "Credits: "
                                + record.getSubject()
                                .getCredits()
                );

                System.out.println(
                        "Marks: " + marks
                );

                System.out.println(
                        "Grade: "
                                + grade.calculateGrade(marks)
                );

                System.out.println(
                        "Grade Point: "
                                + grade.calculateGradePoint(marks)
                );

                System.out.println(
                        "Attendance: "
                                + record.getAttendance()
                                + "%"
                );

                System.out.println(
                        "----------------------------------------"
                );
            }

            double average =
                    analyzer.calculateAverage(records);

            double gpa =
                    analyzer.calculateGPA(records);

            System.out.println(
                    "Average Marks: "
                            + String.format("%.2f", average)
            );

            System.out.println(
                    "GPA: "
                            + String.format("%.2f", gpa)
            );

            System.out.println(
                    "Performance Status: "
                            + analyzer.getPerformanceStatus(records)
            );
        }

        System.out.println(
                "========================================"
        );
    }
}
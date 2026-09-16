package src.service;

import src.model.academicRecord;
import src.model.grade;

import java.util.ArrayList;

public class performanceAnalyzer {

    public double calculateAverage(ArrayList<academicRecord> records) {

        if (records.isEmpty()) {
            return 0;
        }

        double total = 0;

        for (academicRecord record : records) {
            total = total + record.getMarks();
        }

        return total / records.size();
    }

    public double calculateGPA(ArrayList<academicRecord> records) {

        if (records.isEmpty()) {
            return 0;
        }

        double totalPoints = 0;
        int totalCredits = 0;

        for (academicRecord record : records) {

            double gradePoint =
                    grade.calculateGradePoint(record.getMarks());

            int credits =
                    record.getSubject().getCredits();

            totalPoints =
                    totalPoints + (gradePoint * credits);

            totalCredits =
                    totalCredits + credits;
        }

        if (totalCredits == 0) {
            return 0;
        }

        return totalPoints / totalCredits;
    }

    public void displayPerformance(
            ArrayList<academicRecord> records) {

        if (records.isEmpty()) {
            System.out.println(
                    "No academic records found."
            );
            return;
        }

        System.out.println(
                "----------------------------------------"
        );

        for (academicRecord record : records) {

            double marks = record.getMarks();

            System.out.println(
                    "Subject: "
                    + record.getSubject().getSubjectName()
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
                    + record.getAttendance() + "%"
            );

            System.out.println(
                    "----------------------------------------"
            );
        }
    }

    public void displayWeakSubjects(
            ArrayList<academicRecord> records) {

        boolean found = false;

        for (academicRecord record : records) {

            if (record.getMarks() < 50) {

                System.out.println(
                        record.getSubject().getSubjectName()
                        + " - Marks: "
                        + record.getMarks()
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No weak subjects found."
            );
        }
    }

    public void displayAttendanceWarnings(
            ArrayList<academicRecord> records) {

        boolean found = false;

        for (academicRecord record : records) {

            if (record.getAttendance() < 75) {

                System.out.println(
                        record.getSubject().getSubjectName()
                        + " - Attendance: "
                        + record.getAttendance()
                        + "%"
                );

                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No attendance warnings."
            );
        }
    }

    public String getPerformanceStatus(
            ArrayList<academicRecord> records) {

        if (records.isEmpty()) {
            return "No academic data available.";
        }

        double average = calculateAverage(records);

        if (average >= 80) {
            return "Excellent Performance";
        } else if (average >= 60) {
            return "Good Performance";
        } else if (average >= 50) {
            return "Average Performance";
        } else {
            return "Needs Improvement";
        }
    }
}
package src.ui;

import src.model.student;
import src.model.Subject;
import src.model.academicRecord;
import src.service.studentManager;
import src.service.performanceAnalyzer;
import src.service.reportGenerator;
import src.service.studentValidator;
import src.service.dataManager;
import src.exception.InvalidDataException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class menu {

    private Scanner scanner;
    private studentManager manager;
    private performanceAnalyzer analyzer;
    private reportGenerator report;
    private studentValidator validator;
    private dataManager data;

    private student currentStudent;

    private HashMap<String, ArrayList<academicRecord>> records;

    public menu() {

        scanner = new Scanner(System.in);

        manager = new studentManager();

        analyzer = new performanceAnalyzer();

        report = new reportGenerator();

        validator = new studentValidator();

        data = new dataManager();

        records = data.loadRecords();
    }

    public void start() {

        int choice;

        do {

            displayMenu();

            System.out.print("Enter your choice: ");

            try {

                choice = scanner.nextInt();
                scanner.nextLine();

            } catch (Exception e) {

                scanner.nextLine();
                choice = -1;
            }

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    searchStudent();
                    break;

                case 3:
                    manager.displayAllStudents();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    addAcademicRecord();
                    break;

                case 7:
                    viewPerformance();
                    break;

                case 8:
                    viewWeakSubjects();
                    break;

                case 9:
                    viewAttendanceWarnings();
                    break;

                case 10:
                    generateReport();
                    break;

                case 11:
                    data.saveStudents(manager.getStudents());
                    data.saveRecords(records);

                    System.out.println(
                            "Data saved successfully."
                    );

                    System.out.println(
                            "Thank you for using the system."
                    );

                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }

        } while (choice != 11);
    }

    private void displayMenu() {

        System.out.println();
        System.out.println(
                "========================================"
        );

        System.out.println(
                "   STUDENT ACADEMIC MANAGEMENT SYSTEM"
        );

        System.out.println(
                "========================================"
        );

        System.out.println("1. Add Student");
        System.out.println("2. Search Student");
        System.out.println("3. Display All Students");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Add Academic Record");
        System.out.println("7. View Performance");
        System.out.println("8. View Weak Subjects");
        System.out.println("9. Attendance Warnings");
        System.out.println("10. Generate Student Report");
        System.out.println("11. Exit");

        System.out.println(
                "========================================"
        );
    }

    private void addStudent() {

        System.out.println();
        System.out.println(
                "---------- ADD STUDENT ----------"
        );

        System.out.print("Enter Registration Number: ");
        String registrationNumber = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Program: ");
        String program = scanner.nextLine();

        System.out.print("Enter Semester: ");
        int semester = scanner.nextInt();
        scanner.nextLine();

        student s = new student(
                registrationNumber,
                name,
                email,
                program,
                semester
        );

        int oldCount = manager.getStudentCount();

        manager.addStudent(s);

        if (manager.getStudentCount() > oldCount) {

            currentStudent = s;

            records.putIfAbsent(
                    s.getRegistrationNumber(),
                    new ArrayList<>()
            );

            data.saveRecords(records);
        }
    }

    private void searchStudent() {

        System.out.println();
        System.out.println(
                "---------- SEARCH STUDENT ----------"
        );

        System.out.print(
                "Enter Registration Number: "
        );

        String registrationNumber =
                scanner.nextLine();

        student s =
                manager.searchStudent(registrationNumber);

        if (s != null) {

            currentStudent = s;

            System.out.println();
            System.out.println("Student Found:");
            System.out.println(s);

        } else {

            System.out.println(
                    "Student not found."
            );
        }
    }

    private void updateStudent() {

        System.out.println();
        System.out.println(
                "---------- UPDATE STUDENT ----------"
        );

        System.out.print(
                "Enter Registration Number: "
        );

        String registrationNumber =
                scanner.nextLine();

        student s =
                manager.searchStudent(registrationNumber);

        if (s == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }

        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter New Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter New Program: ");
        String program = scanner.nextLine();

        System.out.print("Enter New Semester: ");
        int semester = scanner.nextInt();
        scanner.nextLine();

        boolean updated =
                manager.updateStudent(
                        registrationNumber,
                        name,
                        email,
                        program,
                        semester
                );

        if (updated) {

            currentStudent =
                    manager.searchStudent(
                            registrationNumber
                    );

            System.out.println(
                    "Student updated successfully."
            );

        } else {

            System.out.println(
                    "Student update failed."
            );
        }
    }

    private void deleteStudent() {

        System.out.println();
        System.out.println(
                "---------- DELETE STUDENT ----------"
        );

        System.out.print(
                "Enter Registration Number: "
        );

        String registrationNumber =
                scanner.nextLine();

        boolean deleted =
                manager.deleteStudent(
                        registrationNumber
                );

        if (deleted) {

            records.remove(registrationNumber);

            data.saveRecords(records);

            if (currentStudent != null &&
                    currentStudent
                            .getRegistrationNumber()
                            .equalsIgnoreCase(
                                    registrationNumber)) {

                currentStudent = null;
            }

            System.out.println(
                    "Student deleted successfully."
            );

        } else {

            System.out.println(
                    "Student not found."
            );
        }
    }

    private void addAcademicRecord() {

        System.out.println();
        System.out.println(
                "---------- ADD ACADEMIC RECORD ----------"
        );

        if (currentStudent == null) {

            System.out.println(
                    "Please search or add a student first."
            );

            return;
        }

        System.out.println(
                "Student: "
                        + currentStudent.getName()
        );

        System.out.println(
                "Registration No.: "
                        + currentStudent
                        .getRegistrationNumber()
        );

        System.out.print("Enter Subject Code: ");
        String subjectCode = scanner.nextLine();

        System.out.print("Enter Subject Name: ");
        String subjectName = scanner.nextLine();

        System.out.print("Enter Credits: ");
        int credits = scanner.nextInt();

        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();

        System.out.print("Enter Attendance (%): ");
        double attendance = scanner.nextDouble();

        scanner.nextLine();

        try {

            validator.validateAcademicRecord(
                    credits,
                    marks,
                    attendance
            );

            Subject subject = new Subject(
                    subjectCode,
                    subjectName,
                    credits
            );

            academicRecord record =
                    new academicRecord(
                            subject,
                            marks,
                            attendance
                    );

            String regNo =
                    currentStudent
                            .getRegistrationNumber();

            records.putIfAbsent(
                    regNo,
                    new ArrayList<>()
            );

            records.get(regNo).add(record);

            data.saveRecords(records);

            System.out.println(
                    "Academic record added successfully."
            );

        } catch (InvalidDataException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    private ArrayList<academicRecord>
    getCurrentRecords() {

        if (currentStudent == null) {
            return new ArrayList<>();
        }

        return records.getOrDefault(
                currentStudent
                        .getRegistrationNumber(),
                new ArrayList<>()
        );
    }

    private void viewPerformance() {

        System.out.println();
        System.out.println(
                "---------- PERFORMANCE ----------"
        );

        if (currentStudent == null) {

            System.out.println(
                    "Please search or add a student first."
            );

            return;
        }

        ArrayList<academicRecord>
                currentRecords =
                getCurrentRecords();

        if (currentRecords.isEmpty()) {

            System.out.println(
                    "No academic records found."
            );

            return;
        }

        System.out.println(
                "Student: "
                        + currentStudent.getName()
        );

        System.out.println();

        analyzer.displayPerformance(
                currentRecords
        );

        double average =
                analyzer.calculateAverage(
                        currentRecords
                );

        double gpa =
                analyzer.calculateGPA(
                        currentRecords
                );

        String status =
                analyzer.getPerformanceStatus(
                        currentRecords
                );

        System.out.println(
                "Average Marks: "
                        + String.format(
                                "%.2f", average
                        )
        );

        System.out.println(
                "GPA: "
                        + String.format(
                                "%.2f", gpa
                        )
        );

        System.out.println(
                "Performance Status: "
                        + status
        );
    }

    private void viewWeakSubjects() {

        System.out.println();
        System.out.println(
                "---------- WEAK SUBJECTS ----------"
        );

        if (currentStudent == null) {

            System.out.println(
                    "Please search or add a student first."
            );

            return;
        }

        ArrayList<academicRecord>
                currentRecords =
                getCurrentRecords();

        if (currentRecords.isEmpty()) {

            System.out.println(
                    "No academic records found."
            );

            return;
        }

        analyzer.displayWeakSubjects(
                currentRecords
        );
    }

    private void viewAttendanceWarnings() {

        System.out.println();
        System.out.println(
                "---------- ATTENDANCE WARNINGS ----------"
        );

        if (currentStudent == null) {

            System.out.println(
                    "Please search or add a student first."
            );

            return;
        }

        ArrayList<academicRecord>
                currentRecords =
                getCurrentRecords();

        if (currentRecords.isEmpty()) {

            System.out.println(
                    "No academic records found."
            );

            return;
        }

        analyzer.displayAttendanceWarnings(
                currentRecords
        );
    }

    private void generateReport() {

        System.out.println();
        System.out.println(
                "---------- STUDENT REPORT ----------"
        );

        if (currentStudent == null) {

            System.out.println(
                    "Please search or add a student first."
            );

            return;
        }

        report.generateStudentReport(
                currentStudent,
                getCurrentRecords()
        );
    }
}
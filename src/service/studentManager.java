package src.service;

import src.model.student;
import src.exception.InvalidDataException;

import java.util.ArrayList;

public class studentManager {

    private ArrayList<student> students;
    private studentValidator validator;
    private dataManager data;

    public studentManager() {

        validator = new studentValidator();
        data = new dataManager();

        students = data.loadStudents();
    }

    public void addStudent(student s) {

        try {

            validator.validateStudent(s);

            if (searchStudent(
                    s.getRegistrationNumber()) != null) {

                System.out.println(
                        "Error: Registration number already exists."
                );

                return;
            }

            students.add(s);

            data.saveStudents(students);

            System.out.println(
                    "Student added successfully."
            );

        } catch (InvalidDataException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }

    public student searchStudent(String registrationNumber) {

        for (student s : students) {

            if (s.getRegistrationNumber()
                    .equalsIgnoreCase(registrationNumber)) {

                return s;
            }
        }

        return null;
    }

    public boolean deleteStudent(String registrationNumber) {

        student s = searchStudent(registrationNumber);

        if (s != null) {

            students.remove(s);

            data.saveStudents(students);

            return true;
        }

        return false;
    }

    public void displayAllStudents() {

        if (students.isEmpty()) {

            System.out.println("No students found.");
            return;
        }

        System.out.println();
        System.out.println(
                "---------- ALL STUDENTS ----------"
        );

        for (student s : students) {
            System.out.println(s);
        }
    }

    public boolean updateStudent(
            String registrationNumber,
            String name,
            String email,
            String program,
            int semester) {

        student s = searchStudent(registrationNumber);

        if (s == null) {
            return false;
        }

        student updatedStudent = new student(
                registrationNumber,
                name,
                email,
                program,
                semester
        );

        try {

            validator.validateStudent(updatedStudent);

            s.setName(name);
            s.setEmail(email);
            s.setProgram(program);
            s.setSemester(semester);

            data.saveStudents(students);

            return true;

        } catch (InvalidDataException e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );

            return false;
        }
    }

    public int getStudentCount() {
        return students.size();
    }

    public ArrayList<student> getStudents() {
        return students;
    }
}
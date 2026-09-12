package src.service;

import src.exception.InvalidDataException;
import src.model.student;

public class studentValidator {

    public void validateStudent(student s) throws InvalidDataException {

        if (s == null) {
            throw new InvalidDataException(
                    "Student data cannot be empty."
            );
        }

        if (s.getRegistrationNumber() == null ||
                s.getRegistrationNumber().trim().isEmpty()) {

            throw new InvalidDataException(
                    "Registration number cannot be empty."
            );
        }

        if (s.getName() == null ||
                s.getName().trim().isEmpty()) {

            throw new InvalidDataException(
                    "Student name cannot be empty."
            );
        }

        if (s.getEmail() == null ||
                s.getEmail().trim().isEmpty()) {

            throw new InvalidDataException(
                    "Email cannot be empty."
            );
        }

        if (!s.getEmail().contains("@")) {
            throw new InvalidDataException(
                    "Please enter a valid email address."
            );
        }

        if (s.getProgram() == null ||
                s.getProgram().trim().isEmpty()) {

            throw new InvalidDataException(
                    "Program cannot be empty."
            );
        }

        if (s.getSemester() < 1 ||
                s.getSemester() > 8) {

            throw new InvalidDataException(
                    "Semester must be between 1 and 8."
            );
        }
    }

    public void validateAcademicRecord(
            int credits,
            double marks,
            double attendance)
            throws InvalidDataException {

        if (credits <= 0) {
            throw new InvalidDataException(
                    "Credits must be greater than 0."
            );
        }

        if (marks < 0 || marks > 100) {
            throw new InvalidDataException(
                    "Marks must be between 0 and 100."
            );
        }

        if (attendance < 0 || attendance > 100) {
            throw new InvalidDataException(
                    "Attendance must be between 0 and 100."
            );
        }
    }
}
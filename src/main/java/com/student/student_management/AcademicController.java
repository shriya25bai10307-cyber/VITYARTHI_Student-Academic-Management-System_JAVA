package com.student.student_management;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import src.model.Subject;
import src.model.academicRecord;
import src.model.student;
import src.service.dataManager;
import src.service.studentManager;
import src.service.studentValidator;

@Controller
public class AcademicController {

    private dataManager data = new dataManager();
    private studentValidator validator = new studentValidator();

    @GetMapping("/academic")
    public String academic(Model model) {

        // Load latest student data
        studentManager manager = new studentManager();

        model.addAttribute(
                "students",
                manager.getStudents()
        );

        model.addAttribute(
                "studentCount",
                manager.getStudentCount()
        );

        HashMap<String, ArrayList<academicRecord>> records =
                data.loadRecords();

        model.addAttribute(
                "records",
                records
        );

        return "academic";
    }

    @GetMapping("/academic/add")
    public String addAcademicPage(Model model) {

        // Load latest student data
        studentManager manager = new studentManager();

        model.addAttribute(
                "students",
                manager.getStudents()
        );

        return "add-academic";
    }

    @PostMapping("/academic/add")
    public String addAcademic(
            @RequestParam String registrationNumber,
            @RequestParam String subjectCode,
            @RequestParam String subjectName,
            @RequestParam int credits,
            @RequestParam double marks,
            @RequestParam double attendance,
            Model model) {

        // Load latest student data
        studentManager manager = new studentManager();

        student selectedStudent =
                manager.searchStudent(registrationNumber);

        if (selectedStudent == null) {

            model.addAttribute(
                    "error",
                    "Student with this registration number does not exist."
            );

            model.addAttribute(
                    "students",
                    manager.getStudents()
            );

            return "add-academic";
        }

        try {

            validator.validateAcademicRecord(
                    credits,
                    marks,
                    attendance
            );

        } catch (Exception e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            model.addAttribute(
                    "students",
                    manager.getStudents()
            );

            return "add-academic";
        }

        Subject subject = new Subject(
                subjectCode,
                subjectName,
                credits
        );

        academicRecord record = new academicRecord(
                subject,
                marks,
                attendance
        );

        HashMap<String, ArrayList<academicRecord>> records =
                data.loadRecords();

        if (!records.containsKey(registrationNumber)) {

            records.put(
                    registrationNumber,
                    new ArrayList<academicRecord>()
            );
        }

        records.get(registrationNumber).add(record);

        data.saveRecords(records);

        return "redirect:/academic";
    }
}
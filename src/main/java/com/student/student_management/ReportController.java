package com.student.student_management;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import src.model.academicRecord;
import src.model.student;
import src.service.dataManager;
import src.service.performanceAnalyzer;
import src.service.studentManager;

@Controller
public class ReportController {

    private dataManager data = new dataManager();
    private performanceAnalyzer analyzer = new performanceAnalyzer();

    @GetMapping("/reports")
    public String reports(Model model) {

        // Load latest student data
        studentManager manager = new studentManager();

        model.addAttribute(
                "students",
                manager.getStudents()
        );

        return "reports";
    }

    @GetMapping("/reports/view")
    public String viewReport(
            @RequestParam String registrationNumber,
            Model model) {

        // Load latest student data
        studentManager manager = new studentManager();

        student selectedStudent =
                manager.searchStudent(registrationNumber);

        HashMap<String, ArrayList<academicRecord>> allRecords =
                data.loadRecords();

        ArrayList<academicRecord> records =
                allRecords.get(registrationNumber);

        if (records == null) {
            records = new ArrayList<>();
        }

        model.addAttribute(
                "student",
                selectedStudent
        );

        model.addAttribute(
                "records",
                records
        );

        model.addAttribute(
                "analyzer",
                analyzer
        );

        return "student-report";
    }
}
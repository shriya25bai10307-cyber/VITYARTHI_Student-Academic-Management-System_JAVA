package com.student.student_management;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import src.model.academicRecord;
import src.service.dataManager;
import src.service.performanceAnalyzer;
import src.service.studentManager;

@Controller
public class PerformanceController {

    private dataManager data = new dataManager();
    private performanceAnalyzer analyzer = new performanceAnalyzer();

    @GetMapping("/performance")
    public String performance(Model model) {

        // Load latest student data
        studentManager manager = new studentManager();

        model.addAttribute(
                "students",
                manager.getStudents()
        );

        HashMap<String, ArrayList<academicRecord>> records =
                data.loadRecords();

        model.addAttribute(
                "records",
                records
        );

        model.addAttribute(
                "analyzer",
                analyzer
        );

        return "performance";
    }
}

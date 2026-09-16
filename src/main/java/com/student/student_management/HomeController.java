package com.student.student_management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import src.service.studentManager;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {

        // Load the latest student data every time dashboard is opened
        studentManager manager = new studentManager();

        model.addAttribute(
                "students",
                manager.getStudents()
        );

        model.addAttribute(
                "studentCount",
                manager.getStudentCount()
        );

        return "index";
    }
}
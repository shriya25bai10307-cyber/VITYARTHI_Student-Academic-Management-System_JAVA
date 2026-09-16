package com.student.student_management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import src.model.student;
import src.service.studentManager;

@Controller
public class StudentController {

    private studentManager manager = new studentManager();

    @GetMapping("/students")
    public String students(org.springframework.ui.Model model) {

        model.addAttribute("students", manager.getStudents());
        model.addAttribute("studentCount", manager.getStudentCount());

        return "students";
    }

    @GetMapping("/students/add")
    public String addStudentPage() {
        return "add-student";
    }

    @PostMapping("/students/add")
    public String addStudent(
            @RequestParam String registrationNumber,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String program,
            @RequestParam int semester) {

        student s = new student(
                registrationNumber,
                name,
                email,
                program,
                semester
        );

        manager.addStudent(s);

        return "redirect:/students";
    }

    @GetMapping("/students/edit")
    public String editStudentPage(
            @RequestParam String registrationNumber,
            org.springframework.ui.Model model) {

        student s = manager.searchStudent(registrationNumber);

        model.addAttribute("student", s);

        return "edit-student";
    }

    @PostMapping("/students/edit")
    public String editStudent(
            @RequestParam String registrationNumber,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String program,
            @RequestParam int semester) {

        manager.updateStudent(
                registrationNumber,
                name,
                email,
                program,
                semester
        );

        return "redirect:/students";
    }

    @GetMapping("/students/delete")
    public String deleteStudent(
            @RequestParam String registrationNumber) {

        manager.deleteStudent(registrationNumber);

        return "redirect:/students";
    }
}
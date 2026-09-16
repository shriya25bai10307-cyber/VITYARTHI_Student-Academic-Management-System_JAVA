# Smart Student Academic Management System

## Overview

Smart Student Academic Management System is a web-based application developed using Java and Spring Boot.

The system helps manage student information, academic records, performance analysis, attendance and student reports through a simple web interface.

## Features

- Add new students
- View all students
- Update student information
- Delete students
- Add academic records
- Store subject marks and attendance
- Calculate grades and grade points
- Calculate average marks
- Calculate GPA
- Identify weak subjects
- Display attendance warnings
- Analyze student performance
- Generate complete academic reports
- Persistent data storage
- Input validation and error handling

## Major Modules

### 1. Student Management
- Add Student
- View Students
- Update Student
- Delete Student

### 2. Academic Management
- Add Subject
- Add Marks
- Add Credits
- Add Attendance
- Store academic records

### 3. Performance Analytics
- Calculate Average Marks
- Calculate GPA
- Calculate Grades
- Identify Weak Subjects
- Display Attendance Warnings
- Display Performance Status

### 4. Student Reports
- View Student Details
- View Subject-wise Performance
- View Grades
- View Grade Points
- View Attendance
- View Average Marks
- View GPA
- View Performance Status

## Technologies Used

- Java 21
- Spring Boot
- Spring MVC
- Thymeleaf
- HTML
- CSS
- Maven
- Visual Studio Code

## Project Structure

```text
student-management
│
├── src
│   └── main
│       ├── java
│       │   ├── com/student/student_management
│       │   │   ├── HomeController.java
│       │   │   ├── StudentController.java
│       │   │   ├── AcademicController.java
│       │   │   ├── PerformanceController.java
│       │   │   └── ReportController.java
│       │   │
│       │   └── src
│       │       ├── model
│       │       ├── service
│       │       └── exception
│       │
│       └── resources
│           └── templates
│
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
├── README.md
└── statement.md

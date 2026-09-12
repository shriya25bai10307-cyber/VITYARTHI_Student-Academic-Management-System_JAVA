# Smart Student Academic Management System

## Overview
Smart Student Academic Management System is a Java-based console
application designed to manage student information and academic
performance efficiently.

## Features

### Student Management
- Add student
- Search student
- Display all students
- Update student
- Delete student

### Academic Management
- Add subject records
- Store marks and attendance
- Calculate grades and grade points

### Performance Analysis
- Calculate average marks
- Calculate GPA
- Identify weak subjects
- Generate attendance warnings
- Display performance status

### Reporting
- Generate student academic reports
- Display complete academic summaries

### Data Persistence
Student and academic records are stored using Java file handling
and serialization.

## Technologies Used
- Java
- Object-Oriented Programming
- Java Collections
- Exception Handling
- File Handling
- Serialization
- VS Code

## Project Structure

```text
src/
├── model/
│   ├── student.java
│   ├── Subject.java
│   ├── academicRecord.java
│   ├── grade.java
│   └── semester.java
│
├── service/
│   ├── studentManager.java
│   ├── studentValidator.java
│   ├── performanceAnalyzer.java
│   ├── reportGenerator.java
│   └── dataManager.java
│
├── exception/
│   └── InvalidDataException.java
│
├── ui/
│   └── menu.java
│
└── Main.java
package src.model;

import java.io.Serializable;

public class academicRecord implements Serializable {

    private Subject subject;
    private double marks;
    private double attendance;

    public academicRecord(Subject subject, double marks, double attendance) {
        this.subject = subject;
        this.marks = marks;
        this.attendance = attendance;
    }

    public Subject getSubject() {
        return subject;
    }

    public double getMarks() {
        return marks;
    }

    public double getAttendance() {
        return attendance;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public void setAttendance(double attendance) {
        this.attendance = attendance;
    }

    @Override
    public String toString() {
        return subject.getSubjectName()
                + " | Marks: " + marks
                + " | Attendance: " + attendance + "%";
    }
}
package src.model;

import java.io.Serializable;

public class student implements Serializable {

    private String registrationNumber;
    private String name;
    private String email;
    private String program;
    private int semester;

    public student(String registrationNumber,
                   String name,
                   String email,
                   String program,
                   int semester) {

        this.registrationNumber = registrationNumber;
        this.name = name;
        this.email = email;
        this.program = program;
        this.semester = semester;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProgram() {
        return program;
    }

    public int getSemester() {
        return semester;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {

        return "Student{"
                + "registrationNumber='"
                + registrationNumber + '\''
                + ", name='" + name + '\''
                + ", email='" + email + '\''
                + ", program='" + program + '\''
                + ", semester=" + semester
                + '}';
    }
}

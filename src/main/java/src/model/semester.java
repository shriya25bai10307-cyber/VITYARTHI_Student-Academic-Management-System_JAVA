package src.model;

import java.util.ArrayList;

public class semester {

    private int semesterNumber;
    private ArrayList<academicRecord> records;

    public semester(int semesterNumber) {
        this.semesterNumber = semesterNumber;
        this.records = new ArrayList<>();
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public ArrayList<academicRecord> getRecords() {
        return records;
    }

    public void addRecord(academicRecord record) {
        records.add(record);
    }

    public void removeRecord(academicRecord record) {
        records.remove(record);
    }

    public void displayRecords() {
        for (academicRecord record : records) {
            System.out.println(record);
        }
    }
}
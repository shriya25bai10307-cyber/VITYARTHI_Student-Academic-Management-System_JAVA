package src.service;

import src.model.student;
import src.model.academicRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class dataManager {

    private static final String STUDENT_FILE = "students.dat";
    private static final String RECORD_FILE = "academic_records.dat";

    public void saveStudents(ArrayList<student> students) {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream(STUDENT_FILE))) {

            output.writeObject(students);

        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<student> loadStudents() {

        File file = new File(STUDENT_FILE);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            return (ArrayList<student>) input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading student data.");
            return new ArrayList<>();
        }
    }

    public void saveRecords(
            HashMap<String, ArrayList<academicRecord>> records) {

        try (ObjectOutputStream output =
                     new ObjectOutputStream(
                             new FileOutputStream(RECORD_FILE))) {

            output.writeObject(records);

        } catch (IOException e) {
            System.out.println("Error saving academic records.");
        }
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, ArrayList<academicRecord>> loadRecords() {

        File file = new File(RECORD_FILE);

        if (!file.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream input =
                     new ObjectInputStream(
                             new FileInputStream(file))) {

            return (HashMap<String, ArrayList<academicRecord>>)
                    input.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading academic records.");
            return new HashMap<>();
        }
    }
}
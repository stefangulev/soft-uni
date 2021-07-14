package classroom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Classroom {
    public List<Student> students;
    public int capacity;


    public Classroom(int capacity) {
        this.capacity = capacity;
        this.students = new ArrayList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public int getStudentCount() {
        return this.students.size();
    }

    public String registerStudent(Student student) {
        String result = String.format("Added student %s %s", student.getFirstName(), student.getLastName());
        if (this.students.size() >= capacity) {
            result = "No seats in the classroom";
            return result;
        }

        boolean studentAlreadyInTheRoom = false;
        for (Student datum : students) {
            if (student.getFirstName().equals(datum.getFirstName()) &&
                    student.getLastName().equals(datum.getLastName()) && student.getBestSubject().equals(datum.getBestSubject())) {
                studentAlreadyInTheRoom = true;
                break;
            }
        }
        if (studentAlreadyInTheRoom) {
            result = "Student is already in the classroom";
            return result;
        }

        this.students.add(student);

        return result;
    }

    public String dismissStudent(Student student) {
        String result = String.format("Removed student %s %s", student.getFirstName(), student.getLastName());

        boolean removedSuccessfully = this.students.removeIf(s -> s.getFirstName().equals(student.getFirstName()) &&
                s.getLastName().equals(student.getLastName()) && s.getBestSubject().equals(student.getBestSubject()));

        if (!removedSuccessfully) {
            result = "Student not found";
        }

        return result;
    }

    public String getSubjectInfo(String subject) {
        String result = "No students enrolled for the subject";
        List<Student> studentsWithSubject = new ArrayList<>();

        for (Student datum : students) {
            if (datum.getBestSubject().equals(subject)) {
                studentsWithSubject.add(datum);
            }
        }

        if (studentsWithSubject.isEmpty()) {
            return result;
        }

        result = String.format("Subject: %s%nStudents:%n", subject);
        result += studentsWithSubject.stream().map(e -> String.valueOf(e.getFirstName() + " " + e.getLastName()))
                .collect(Collectors.joining(System.lineSeparator()));

        return result;


    }

    public Student getStudent(String firstName, String lastName) {

        Student student = null;

        for (Student datum : students) {
            if (datum.getFirstName().equals(firstName) && datum.getLastName().equals(lastName)) {
                student = datum;
            }
        }

        return student;

    }

    public String getStatistics() {
        String result = String.format("Classroom size: %d%n", this.students.size());

        result += this.students.stream().map(student-> "==" + String.valueOf(student)).collect(Collectors.joining(System.lineSeparator()));

        return result;
    }




}

package com.example.courseras.models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "student_courses_xref")
public class Xref implements Serializable {
    private Student student;
    private Courses courses;
    private LocalDateTime completion_date;
    @Id
    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Id
    @ManyToOne
    public Courses getCourses() {
        return courses;
    }

    public void setCourses(Courses courses) {
        this.courses = courses;
    }

    public LocalDateTime getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(LocalDateTime completion_date) {
        this.completion_date = completion_date;
    }
}

package com.example.hateoasdemo.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private Course course;
    private Student student;

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public Order setCourse(Course course) {
        this.course = course;
        return this;
    }

    @ManyToOne
    public Student getStudent() {
        return student;
    }

    public Order setStudent(Student student) {
        this.student = student;
        return this;
    }
}

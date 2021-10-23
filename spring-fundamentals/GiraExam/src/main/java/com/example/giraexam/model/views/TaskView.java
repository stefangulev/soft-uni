package com.example.giraexam.model.views;

import com.example.giraexam.model.entities.Classification;
import com.example.giraexam.model.entities.User;
import com.example.giraexam.model.enums.ProgressEnum;

import java.util.Date;

public class TaskView {
    private Long id;
    private String name;
    private ProgressEnum progress;
    private Date dueDate;
    private Classification classification;
    private User user;

    public Long getId() {
        return id;
    }

    public TaskView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TaskView setName(String name) {
        this.name = name;
        return this;
    }

    public ProgressEnum getProgress() {
        return progress;
    }

    public TaskView setProgress(ProgressEnum progress) {
        this.progress = progress;
        return this;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public TaskView setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public Classification getClassification() {
        return classification;
    }

    public TaskView setClassification(Classification classification) {
        this.classification = classification;
        return this;
    }

    public User getUser() {
        return user;
    }

    public TaskView setUser(User user) {
        this.user = user;
        return this;
    }
}

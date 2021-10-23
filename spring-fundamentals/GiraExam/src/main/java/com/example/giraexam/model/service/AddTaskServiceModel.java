package com.example.giraexam.model.service;

import com.example.giraexam.model.enums.ClassificationNameEnum;

import java.time.Instant;
import java.util.Date;

public class AddTaskServiceModel {
    private String name;
    private String description;
    private Date dueDate;
    private ClassificationNameEnum classification;

    public String getName() {
        return name;
    }

    public AddTaskServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddTaskServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public AddTaskServiceModel setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public ClassificationNameEnum getClassification() {
        return classification;
    }

    public AddTaskServiceModel setClassification(ClassificationNameEnum classification) {
        this.classification = classification;
        return this;
    }
}

package com.example.giraexam.model.entities;

import com.example.giraexam.model.enums.ProgressEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    private String name;
    private String description;
    private ProgressEnum progress;
    private Date dueDate;
    private Classification classification;
    private User user;

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public Task setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Task setDescription(String description) {
        this.description = description;
        return this;
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public ProgressEnum getProgress() {
        return progress;
    }

    public Task setProgress(ProgressEnum progress) {
        this.progress = progress;
        return this;
    }

    @Column(nullable = false, name = "due_date")
    public Date getDueDate() {
        return dueDate;
    }

    public Task setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }
    @NotNull
    @ManyToOne
    public Classification getClassification() {
        return classification;
    }

    public Task setClassification(Classification classification) {
        this.classification = classification;
        return this;
    }
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public Task setUser(User user) {
        this.user = user;
        return this;
    }
}

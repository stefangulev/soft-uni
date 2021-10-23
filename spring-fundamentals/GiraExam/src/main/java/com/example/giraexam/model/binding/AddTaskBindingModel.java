package com.example.giraexam.model.binding;

import com.example.giraexam.model.enums.ClassificationNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Date;

public class AddTaskBindingModel {
    private String name;
    private String description;
    private Date dueDate;
    private ClassificationNameEnum classification;

    @NotBlank
    @Size(min = 3, max = 20)
    public String getName() {
        return name;
    }

    public AddTaskBindingModel setName(String name) {
        this.name = name;
        return this;
    }
    @NotBlank
    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public AddTaskBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDueDate() {
        return dueDate;
    }

    public AddTaskBindingModel setDueDate(Date dueDate) {
        this.dueDate = dueDate;
        return this;
    }
    @NotNull
    public ClassificationNameEnum getClassification() {
        return classification;
    }

    public AddTaskBindingModel setClassification(ClassificationNameEnum classification) {
        this.classification = classification;
        return this;
    }
}

package com.example.giraexam.model.entities;

import com.example.giraexam.model.enums.ClassificationNameEnum;

import javax.persistence.*;

@Entity
@Table(name = "classifications")
public class Classification extends BaseEntity {
    private ClassificationNameEnum name;
    private String description;


    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    public ClassificationNameEnum getName() {
        return name;
    }

    public Classification setName(ClassificationNameEnum name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public Classification setDescription(String description) {
        this.description = description;
        return this;
    }
}

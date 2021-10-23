package com.example.coffeeshop.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoryNameEnum name;
    @Column(name = "needed_time", nullable = false)
    private Integer neededTime;

    public CategoryNameEnum getName() {
        return name;
    }

    public Category setName(CategoryNameEnum name) {
        this.name = name;
        return this;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public Category setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
        return this;
    }
}

package com.example.hateoasdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    private String name;
    private Double price;
    private boolean enabled;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Course setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public Double getPrice() {
        return price;
    }

    public Course setPrice(Double price) {
        this.price = price;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Course setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}

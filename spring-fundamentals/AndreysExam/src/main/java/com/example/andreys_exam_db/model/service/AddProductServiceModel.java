package com.example.andreys_exam_db.model.service;

import com.example.andreys_exam_db.model.enums.CategoryEnum;
import com.example.andreys_exam_db.model.enums.SexEnum;

import java.math.BigDecimal;

public class AddProductServiceModel {
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryEnum category;
    private SexEnum sex;

    public String getName() {
        return name;
    }

    public AddProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddProductServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public AddProductServiceModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public AddProductServiceModel setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }
}

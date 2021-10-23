package com.example.andreys_exam_db.model.view;

import com.example.andreys_exam_db.model.enums.CategoryEnum;
import com.example.andreys_exam_db.model.enums.SexEnum;

import java.util.UUID;

public class ProductDetailsView {
    private UUID id;
    private String name;
    private String price;
    private CategoryEnum category;
    private SexEnum sex;
    private String description;

    public String getDescription() {
        return description;
    }

    public ProductDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ProductDetailsView setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public ProductDetailsView setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public ProductDetailsView setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDetailsView setName(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public ProductDetailsView setPrice(String price) {
        this.price = price;
        return this;
    }
}

package com.example.andreys_exam_db.model.view;

import com.example.andreys_exam_db.model.enums.CategoryEnum;
import com.example.andreys_exam_db.model.enums.SexEnum;

import java.util.UUID;

public class ProductView {
    private UUID id;
    private String name;
    private String price;
    private CategoryEnum category;
    private SexEnum sex;

    public CategoryEnum getCategory() {
        return category;
    }

    public ProductView setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public SexEnum getSex() {
        return sex;
    }

    public ProductView setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public ProductView setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductView setName(String name) {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public ProductView setPrice(String price) {
        this.price = price;
        return this;
    }
}

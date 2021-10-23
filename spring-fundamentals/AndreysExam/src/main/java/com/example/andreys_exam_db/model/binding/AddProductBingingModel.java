package com.example.andreys_exam_db.model.binding;

import com.example.andreys_exam_db.model.enums.CategoryEnum;
import com.example.andreys_exam_db.model.enums.SexEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class AddProductBingingModel {
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryEnum category;
    private SexEnum sex;

    @NotBlank
    public String getName() {
        return name;
    }

    public AddProductBingingModel setName(String name) {
        this.name = name;
        return this;
    }
    @NotBlank
    public String getDescription() {
        return description;
    }

    public AddProductBingingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public AddProductBingingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotNull
    public CategoryEnum getCategory() {
        return category;
    }

    public AddProductBingingModel setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    @NotNull
    public SexEnum getSex() {
        return sex;
    }

    public AddProductBingingModel setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }
}

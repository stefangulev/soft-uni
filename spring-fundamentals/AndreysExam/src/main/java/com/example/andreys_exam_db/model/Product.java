package com.example.andreys_exam_db.model;

import com.example.andreys_exam_db.model.enums.CategoryEnum;
import com.example.andreys_exam_db.model.enums.SexEnum;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    private String name;
    private String description;
    private BigDecimal price;
    private CategoryEnum category;
    private SexEnum sex;
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    @Column(columnDefinition = "TEXT", nullable = false)
    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public CategoryEnum getCategory() {
        return category;
    }

    public Product setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public SexEnum getSex() {
        return sex;
    }

    public Product setSex(SexEnum sex) {
        this.sex = sex;
        return this;
    }
}

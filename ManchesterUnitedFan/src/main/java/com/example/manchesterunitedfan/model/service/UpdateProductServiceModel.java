package com.example.manchesterunitedfan.model.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class UpdateProductServiceModel {
    private Long id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public UpdateProductServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    @Size(min = 3)
    public String getName() {
        return name;
    }

    public UpdateProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Positive
    public Integer getQuantity() {
        return quantity;
    }

    public UpdateProductServiceModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @NotNull
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public UpdateProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public UpdateProductServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}

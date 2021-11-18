package com.example.manchesterunitedfan.model.service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddProductServiceModel {
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String imgUrl;

    public String getName() {
        return name;
    }

    public AddProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public AddProductServiceModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public AddProductServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}

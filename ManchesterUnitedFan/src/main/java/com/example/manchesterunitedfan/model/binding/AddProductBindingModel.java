package com.example.manchesterunitedfan.model.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class AddProductBindingModel {

    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String imgUrl;

    @NotBlank
    @Size(min = 3)
    public String getName() {
        return name;
    }

    public AddProductBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @PositiveOrZero
    public Integer getQuantity() {
        return quantity;
    }

    public AddProductBindingModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @NotNull
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public AddProductBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public AddProductBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}

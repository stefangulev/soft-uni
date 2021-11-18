package com.example.manchesterunitedfan.model.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class UpdateProductBindingModel {
    private Long id;
    private String name;
    private Integer quantity;
    private BigDecimal price;
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public UpdateProductBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    @NotBlank
    @Size(min = 3)
    public String getName() {
        return name;
    }

    public UpdateProductBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @PositiveOrZero
    public Integer getQuantity() {
        return quantity;
    }

    public UpdateProductBindingModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    @NotNull
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public UpdateProductBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public UpdateProductBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}

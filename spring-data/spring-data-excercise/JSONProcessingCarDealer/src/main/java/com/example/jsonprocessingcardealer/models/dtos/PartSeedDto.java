package com.example.jsonprocessingcardealer.models.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class PartSeedDto {
    @Expose
    private String name;
    @Expose
    private BigDecimal price;
    @Expose
    private Long quantity;

    public PartSeedDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}

package com.example.themarket.model.dtos;

import java.math.BigDecimal;

public class CreateContractServiceDto {
    private Long itemId;
    private BigDecimal price;

    public Long getItemId() {
        return itemId;
    }

    public CreateContractServiceDto setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateContractServiceDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}

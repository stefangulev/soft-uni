package com.example.themarket.model.dtos;

import java.math.BigDecimal;

public class CreateContractControllerDto {
    private Long itemId;
    private BigDecimal price;

    public Long getItemId() {
        return itemId;
    }

    public CreateContractControllerDto setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateContractControllerDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}

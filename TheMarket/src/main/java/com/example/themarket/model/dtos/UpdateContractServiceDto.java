package com.example.themarket.model.dtos;

import java.math.BigDecimal;

public class UpdateContractServiceDto {
    private Long itemId;
    private BigDecimal price;

    public Long getItemId() {
        return itemId;
    }

    public UpdateContractServiceDto setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UpdateContractServiceDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}

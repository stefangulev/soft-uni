package com.example.themarket.model.dtos;

import java.math.BigDecimal;

public class UpdateContractControllerDto {
    private Long itemId;
    private BigDecimal price;

    public Long getItemId() {
        return itemId;
    }

    public UpdateContractControllerDto setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UpdateContractControllerDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}

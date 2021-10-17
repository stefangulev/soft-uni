package com.example.themarket.model.dtos;


import java.math.BigDecimal;

public class GetActiveContractDto {
    private Long sellerId;
    private Long itemId;
    private BigDecimal price;
    private boolean active;

    public Long getSellerId() {
        return sellerId;
    }

    public GetActiveContractDto setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public GetActiveContractDto setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public GetActiveContractDto setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public GetActiveContractDto setActive(boolean active) {
        this.active = active;
        return this;
    }
}

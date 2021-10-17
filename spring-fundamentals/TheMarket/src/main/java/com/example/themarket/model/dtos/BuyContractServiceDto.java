package com.example.themarket.model.dtos;

public class BuyContractServiceDto {
    private Long itemId;
    private Long buyerId;

    public Long getItemId() {
        return itemId;
    }

    public BuyContractServiceDto setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public BuyContractServiceDto setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }
}

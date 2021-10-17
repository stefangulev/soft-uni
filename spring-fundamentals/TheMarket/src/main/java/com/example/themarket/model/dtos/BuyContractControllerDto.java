package com.example.themarket.model.dtos;

public class BuyContractControllerDto {
    private Long itemId;
    private Long buyerId;

    public Long getItemId() {
        return itemId;
    }

    public BuyContractControllerDto setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public BuyContractControllerDto setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }
}

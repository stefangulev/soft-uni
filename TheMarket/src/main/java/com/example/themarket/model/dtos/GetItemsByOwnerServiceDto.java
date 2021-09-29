package com.example.themarket.model.dtos;

public class GetItemsByOwnerServiceDto {
    private Long ownerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public GetItemsByOwnerServiceDto setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}

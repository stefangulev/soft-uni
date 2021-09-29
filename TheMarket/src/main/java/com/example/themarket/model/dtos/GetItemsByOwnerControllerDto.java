package com.example.themarket.model.dtos;

public class GetItemsByOwnerControllerDto {
    private Long ownerId;

    public Long getOwnerId() {
        return ownerId;
    }

    public GetItemsByOwnerControllerDto setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}

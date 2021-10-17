package com.example.themarket.model.dtos;

public class CreateItemControllerDto {
    private String name;
    private Long ownerId;

    public String getName() {
        return name;
    }

    public CreateItemControllerDto setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public CreateItemControllerDto setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}

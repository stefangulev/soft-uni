package com.example.themarket.model.dtos;

public class CreateItemServiceDto {
    private String name;
    private Long ownerId;

    public String getName() {
        return name;
    }

    public CreateItemServiceDto setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public CreateItemServiceDto setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}

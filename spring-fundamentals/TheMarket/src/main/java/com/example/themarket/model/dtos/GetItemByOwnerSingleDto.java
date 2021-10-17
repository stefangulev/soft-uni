package com.example.themarket.model.dtos;

public class GetItemByOwnerSingleDto {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public GetItemByOwnerSingleDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GetItemByOwnerSingleDto setName(String name) {
        this.name = name;
        return this;
    }
}

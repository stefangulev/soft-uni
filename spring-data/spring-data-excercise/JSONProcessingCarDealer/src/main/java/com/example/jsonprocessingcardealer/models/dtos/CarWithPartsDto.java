package com.example.jsonprocessingcardealer.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.Set;

public class CarWithPartsDto {
    @Expose
    private String make;
    @Expose
    private String model;
    @Expose
    private Long travelledDistance;
    @Expose
    private Set<PartsNameAndPriceDto> parts;

    public CarWithPartsDto() {

    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public Set<PartsNameAndPriceDto> getParts() {
        return parts;
    }

    public void setParts(Set<PartsNameAndPriceDto> parts) {
        this.parts = parts;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;

    }
}

package bg.softuni.mobilelele.model.service;

import bg.softuni.mobilelele.model.entities.EngineType;
import bg.softuni.mobilelele.model.entities.TransmissionType;

public class OfferCreateServiceModel {
    private String description;
    private String model;
    private EngineType engine;
    private String imageUrl;
    private int mileage;
    private int price;
    private TransmissionType transmission;
    private int year;
    private Long sellerId;

    public Long getSellerId() {
        return sellerId;
    }

    public OfferCreateServiceModel setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferCreateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineType getEngine() {
        return engine;
    }

    public OfferCreateServiceModel setEngine(EngineType engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferCreateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferCreateServiceModel setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public OfferCreateServiceModel setPrice(int price) {
        this.price = price;
        return this;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public OfferCreateServiceModel setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferCreateServiceModel setYear(int year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferCreateServiceModel setModel(String model) {
        this.model = model;
        return this;
    }
}

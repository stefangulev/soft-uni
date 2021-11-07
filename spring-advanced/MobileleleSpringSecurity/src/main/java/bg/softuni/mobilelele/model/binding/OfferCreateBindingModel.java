package bg.softuni.mobilelele.model.binding;

import bg.softuni.mobilelele.model.entities.EngineType;
import bg.softuni.mobilelele.model.entities.TransmissionType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class OfferCreateBindingModel {
    @NotNull
    private String description;
    @NotBlank
    private String model;
    @NotNull
    private EngineType engine;
    @NotBlank
    private String imageUrl;
    @NotNull
    @PositiveOrZero
    private int mileage;
    @NotNull
    @Min(100)
    private int price;
    @NotNull
    private TransmissionType transmission;
   @NotNull
   @Min(1930)
    private int year;

    public String getDescription() {
        return description;
    }

    public OfferCreateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineType getEngine() {
        return engine;
    }

    public OfferCreateBindingModel setEngine(EngineType engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferCreateBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferCreateBindingModel setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public OfferCreateBindingModel setPrice(int price) {
        this.price = price;
        return this;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public OfferCreateBindingModel setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferCreateBindingModel setYear(int year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferCreateBindingModel setModel(String model) {
        this.model = model;
        return this;
    }
}

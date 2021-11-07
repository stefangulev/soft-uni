package bg.softuni.mobilelele.model.binding;

import bg.softuni.mobilelele.model.entities.EngineType;
import bg.softuni.mobilelele.model.entities.TransmissionType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class OfferUpdateBindingModel {
    private Long id;
    @NotNull
    private String description;
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

    public OfferUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineType getEngine() {
        return engine;
    }

    public OfferUpdateBindingModel setEngine(EngineType engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferUpdateBindingModel setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public OfferUpdateBindingModel setPrice(int price) {
        this.price = price;
        return this;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public OfferUpdateBindingModel setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferUpdateBindingModel setYear(int year) {
        this.year = year;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }
}

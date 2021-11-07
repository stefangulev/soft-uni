package bg.softuni.mobilelele.model.view;

import bg.softuni.mobilelele.model.entities.EngineType;
import bg.softuni.mobilelele.model.entities.TransmissionType;

public class OfferSummaryView {

    private Long id;
        private String description;
        private EngineType engine;
        private String imageUrl;
        private int mileage;
        private int price;
        private TransmissionType transmission;
        private int year;
        private String model;

        public String getDescription() {
            return description;
        }

        public OfferSummaryView setDescription(String description) {
            this.description = description;
            return this;
        }

        public EngineType getEngine() {
            return engine;
        }

        public OfferSummaryView setEngine(EngineType engine) {
            this.engine = engine;
            return this;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public OfferSummaryView setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public int getMileage() {
            return mileage;
        }

        public OfferSummaryView setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public int getPrice() {
            return price;
        }

        public OfferSummaryView setPrice(int price) {
            this.price = price;
            return this;
        }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public OfferSummaryView setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
            return year;
        }

        public OfferSummaryView setYear(int year) {
            this.year = year;
            return this;
        }

        public String getModel() {
            return model;
        }

        public OfferSummaryView setModel(String model) {
            this.model = model;
            return this;
        }

    public Long getId() {
        return id;
    }

    public OfferSummaryView setId(Long id) {
        this.id = id;
        return this;
    }
}


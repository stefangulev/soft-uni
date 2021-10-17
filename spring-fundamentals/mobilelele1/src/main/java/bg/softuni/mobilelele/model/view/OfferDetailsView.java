package bg.softuni.mobilelele.model.view;

import bg.softuni.mobilelele.model.entities.EngineType;
import bg.softuni.mobilelele.model.entities.TransmissionType;
import bg.softuni.mobilelele.model.entities.UserEntity;

import java.time.Instant;

public class OfferDetailsView {

    private Long id;
        private String description;
        private EngineType engine;
        private String imageUrl;
        private int mileage;
        private int price;
        private TransmissionType transmission;
        private int year;
        private String model;
    private Instant created;
    private Instant modified;
    private String sellerFullName;

        public String getDescription() {
            return description;
        }

        public OfferDetailsView setDescription(String description) {
            this.description = description;
            return this;
        }

        public EngineType getEngine() {
            return engine;
        }

        public OfferDetailsView setEngine(EngineType engine) {
            this.engine = engine;
            return this;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public OfferDetailsView setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public int getMileage() {
            return mileage;
        }

        public OfferDetailsView setMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public int getPrice() {
            return price;
        }

        public OfferDetailsView setPrice(int price) {
            this.price = price;
            return this;
        }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public OfferDetailsView setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
            return year;
        }

        public OfferDetailsView setYear(int year) {
            this.year = year;
            return this;
        }

        public String getModel() {
            return model;
        }

        public OfferDetailsView setModel(String model) {
            this.model = model;
            return this;
        }

    public Long getId() {
        return id;
    }

    public OfferDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public OfferDetailsView setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public OfferDetailsView setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public String getSellerFullName() {
        return sellerFullName;
    }

    public OfferDetailsView setSellerFullName(String sellerFullName) {
        this.sellerFullName = sellerFullName;
        return this;
    }
}


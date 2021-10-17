package bg.softuni.mobilelele.model.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModelCategory category;
    @Column(name = "image_url", nullable = false)
    private String imageUrl;
    @Column(name = "start_year", nullable = false)
    private Integer startYear;
    @Column(name = "end_year")
    private Integer endYear;
    @Column(nullable = false)
    private Instant created;
    private Instant modified;
    @ManyToOne(cascade = CascadeType.ALL)
    private BrandEntity brand;
    @OneToMany(mappedBy = "model")
    private Set<OfferEntity> offers;

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public ModelCategory getCategory() {
        return category;
    }

    public ModelEntity setCategory(ModelCategory category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public ModelEntity setStartYear(Integer startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public ModelEntity setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public ModelEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public ModelEntity setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ModelEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }
}

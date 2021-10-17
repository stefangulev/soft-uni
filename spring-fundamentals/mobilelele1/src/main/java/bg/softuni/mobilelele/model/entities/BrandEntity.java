package bg.softuni.mobilelele.model.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "brands")
public class BrandEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Instant created;
    @Column
    private Instant modified;
    @OneToMany(mappedBy = "brand")
    private Set<ModelEntity> models;

    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public BrandEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public Instant getModified() {
        return modified;
    }

    public BrandEntity setModified(Instant modified) {
        this.modified = modified;
        return this;
    }

    public Set<ModelEntity> getModels() {
        return models;
    }

    public void setModels(Set<ModelEntity> models) {
        this.models = models;
    }
}

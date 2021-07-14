import entity.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "store_locations")
public class StoreLocation extends BaseEntity {
    private String locationName;
    private Set<Sale> sales;

    public StoreLocation() {
        this.sales = new LinkedHashSet<>();
    }

    @OneToMany(mappedBy = "storeLocation")
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}

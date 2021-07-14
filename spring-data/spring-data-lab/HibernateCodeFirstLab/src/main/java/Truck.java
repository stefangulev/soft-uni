import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "trucks")
public class Truck extends Vehicle {
    @Column(name = "load_capacity")
    private double loadCapacity;

    @ManyToMany
    private Set<Driver> drivers = new LinkedHashSet<>();


    public void addDriver(Driver driver) {
        this.drivers.add(driver);
    }

    public Truck () {

    }

    public Truck (String model, BigDecimal price, String fuelType, double loadCapacity) {
        super("TRUCK", model, price, fuelType);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }
}


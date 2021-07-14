import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "planes")
public class Plane extends Vehicle{
    @Column(name = "passenger_capacity")
    private int passengerCapacity;

    @ManyToOne
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Plane() {

    }
    public Plane(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public Plane( String model, BigDecimal price, String fuelType, int passengerCapacity) {
        super("PLANE", model, price, fuelType);
        this.passengerCapacity = passengerCapacity;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }
}

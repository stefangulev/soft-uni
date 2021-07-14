import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle{

    public Bike() {
    }

    public Bike(String model, BigDecimal price, String fuelType) {
        super("BIKE", model, price, fuelType);
    }

}

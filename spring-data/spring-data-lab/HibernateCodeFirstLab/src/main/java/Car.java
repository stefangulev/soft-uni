import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {
    private int seats;
    @OneToOne
    @JoinColumn(name = "plate_number_id")
    private PlateNumber number;

    public Car() {

    }
    public Car(String model, BigDecimal price, String fuelType, int seats, PlateNumber number) {
        super("CAR", model, price, fuelType);
        this.seats = seats;
        this.number = number;

    }



    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}

package vehicle;

import java.text.DecimalFormat;

public class Car extends Vehicle {

    public Car(double fuelQuantity, double fuelConsumptionPerKm, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionPerKm + 0.9, tankCapacity);
    }


    @Override
    public String drive(Double distance) {
       return "Car" + super.drive(distance);
    }

}

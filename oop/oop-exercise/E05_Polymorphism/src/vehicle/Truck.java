package vehicle;

public class Truck extends Vehicle {
    protected Truck(double fuelQuantity, double fuelConsumptionInLitersPerKm, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionInLitersPerKm + 1.6, tankCapacity);
    }

    @Override
    public String drive(Double distance) {
        return "Truck" + super.drive(distance);
    }

    @Override
    public void refuel(Double liters) {
       super.refuel(liters * 0.95);
    }
}

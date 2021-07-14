package vehicle;

public class Bus extends Vehicle {


    public Bus(double fuelQuantity, double fuelConsumptionInLitersPerKm, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionInLitersPerKm, tankCapacity);
    }


    public String drive(Double distance, String driveType) {
        if (driveType.equals("Drive")) {
            double increasedFuelConsumption = getFuelConsumptionInLitersPerKm() + 1.4;
            return "Bus" + super.drive(distance, increasedFuelConsumption);
        } else {
            return "Bus" + super.drive(distance);
        }
    }
}

package vehicle;

import java.text.DecimalFormat;

public class Vehicle {
    private double fuelQuantity;
    private double fuelConsumptionInLitersPerKm;
    private double tankCapacity;
    private DecimalFormat formatter;

    protected Vehicle(double fuelQuantity, double fuelConsumptionInLitersPerKm, double tankCapacity) {
        setFuelQuantity(fuelQuantity);
        setFuelConsumptionInLitersPerKm(fuelConsumptionInLitersPerKm);
        setTankCapacity(tankCapacity);
        this.formatter = new DecimalFormat("##.##");
    }

    protected void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    protected double getTankCapacity() {
        return tankCapacity;
    }

    protected void setTankCapacity(double tankCapacity) {
        this.tankCapacity = tankCapacity;
    }

    protected void setFuelConsumptionInLitersPerKm(double fuelConsumptionInLitersPerKm) {
        this.fuelConsumptionInLitersPerKm = fuelConsumptionInLitersPerKm;
    }

    public DecimalFormat getFormatter() {
        return formatter;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    protected double getFuelConsumptionInLitersPerKm() {
        return fuelConsumptionInLitersPerKm;
    }

    public String drive(Double distance) {
        if (distance * getFuelConsumptionInLitersPerKm() > getFuelQuantity()) {
            return " needs refueling";
        } else {
            setFuelQuantity(getFuelQuantity() - (getFuelConsumptionInLitersPerKm() * distance));
            return String.format(" travelled %s km", getFormatter().format(distance));
        }
    }

    public String drive(Double distance, Double increasedFuelPerKm) {
        if (distance * increasedFuelPerKm > getFuelQuantity()) {
            return " needs refueling";
        } else {
            setFuelQuantity(getFuelQuantity() - (increasedFuelPerKm * distance));
            return String.format(" travelled %s km", getFormatter().format(distance));
        }
    }

    public void refuel(Double liters) {
        if (liters < 1) {
            System.out.println("Fuel must be a positive number");
        } else if (liters + this.getFuelQuantity() > tankCapacity) {
            System.out.println("Cannot fit fuel in tank");
        } else {
            setFuelQuantity(getFuelQuantity() + liters);
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f", this.getClass().getSimpleName(), this.getFuelQuantity());
    }
}

package CarSalesman;

public class Engine {
    String model;
    int power;
    int displacement;
    String efficiency;


    public Engine (String model , int power) {
        this.model = model;
        this.power = power;

    }

    public Engine (String model, int power, int displacement) {
        this(model, power);
        this.displacement = displacement;
    }

    public Engine (String model, int power, String efficiency) {
        this(model, power);
        this.efficiency = efficiency;
    }

    public Engine (String model, int power, int displacement, String efficiency) {
        this(model, power, displacement);
        this.efficiency = efficiency;

    }

    public int getPower() {
        return power;
    }

    public String getModel() {
        return model;
    }

    public int getDisplacement() {
        return displacement;
    }

    public String getEfficiency() {
        return efficiency;
    }

    @Override
    public String toString() {
        return String.format("%s:%n" +
                "Power: %s%n" + "Displacement: %s%n" + "Efficiency: %s%n", this.model, this.power,
                this.displacement == 0 ? "n/a" : String.valueOf(this.displacement), this.efficiency == null ? "n/a" : this.efficiency);
    }
}

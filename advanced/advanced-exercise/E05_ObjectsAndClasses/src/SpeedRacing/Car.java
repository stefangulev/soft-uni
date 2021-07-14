package SpeedRacing;

public class Car {
    String model;
    double fuelAmount;
    double fuelCostPerKm;
    int distanceTraveled;

    public Car (String model, double fuelAmount, double fuelCostPerKm) {
        this.model = model;
        this.fuelAmount = fuelAmount;
        this.fuelCostPerKm = fuelCostPerKm;
       this.distanceTraveled = 0;
    }



    public void driveCar(int distanceToTravel) {
        double fuelCostForTravel = this.fuelCostPerKm * distanceToTravel;
        if (fuelCostForTravel <= this.fuelAmount) {
            this.fuelAmount = this.fuelAmount - fuelCostForTravel;
            this.distanceTraveled += distanceToTravel;
        } else {
            System.out.println("Insufficient fuel for the drive");
        }
    }


    public String getModel() {
        return this.model;
    }

    @Override
    public String toString() {
        return String.format("%s %.2f %d", this.model, this.fuelAmount, this.distanceTraveled);
    }
}

package RawData;

import java.util.Arrays;
import java.util.List;

public class Tire {
    double tire1Pressure;
    double tire2Pressure;
    double tire3Pressure;
    double tire4Pressure;
    int tire1Age;
    int tire2Age;
    int tire3Age;
    int tire4Age;


public Tire (double tire1Pressure, int tire1Age, double tire2Pressure,
             int tire2Age, double tire3Pressure, int tire3Age, double tire4Pressure, int tire4Age) {
    this.tire1Pressure = tire1Pressure;
    this.tire1Age = tire1Age;
    this.tire2Pressure = tire2Pressure;
    this.tire2Age = tire2Age;
    this.tire3Pressure = tire3Pressure;
    this.tire3Age = tire3Age;
    this.tire4Pressure = tire4Pressure;
    this.tire4Age = tire4Age;
}

    public double getMinTirePressure() {
        double first = Double.min(tire1Pressure, tire2Pressure);
        double second = Double.min(tire2Pressure, tire3Pressure);
        return Double.min(first, second);

    }



}

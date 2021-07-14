package RawData;

public class Car {

    String model;
    Engine engine;
    Cargo cargo;
    Tire tire;

    public Car(String model, int speed, int power, int weight, String type, double tire1Pressure, int tire1Age, double tire2Pressure,
               int tire2Age, double tire3Pressure, int tire3Age, double tire4Pressure, int tire4Age) {
        this.model = model;
        this.engine = new Engine(speed, power);
        this.cargo = new Cargo(weight, type);
        this.tire =  new Tire(tire1Pressure, tire1Age, tire2Pressure, tire2Age, tire3Pressure, tire3Age, tire4Pressure, tire4Age);


    }

    public String getModel() {
        return this.model;
    }
    public Engine getEngine() {
        return this.engine;
    }
    public Cargo getCargo() {
        return this.cargo;
    }
    public Tire getTire() {
        return this.tire;
    }

}

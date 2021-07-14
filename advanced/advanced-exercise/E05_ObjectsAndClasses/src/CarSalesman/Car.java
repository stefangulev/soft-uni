package CarSalesman;

public class Car {
    String model;
    Engine engine;
    int weight;
    String color;


    public Car(String model, Engine engine) {
        this.model = model;
        this.engine = engine;
    }

    public Car(String model, Engine engine, int weight) {
        this(model, engine);
        this.weight = weight;

    }

    public  Car(String model, Engine engine, String color) {
        this (model, engine);
        this.color = color;
    }
    public Car(String model, Engine engine, int weight, String color) {
        this(model, engine, weight);
        this.color = color;
    }

    @Override
    public String toString() {
        return this.model + ":" +'\n' + engine.toString() + String.format("Weight: %s%n",
                this.weight == 0 ? "n/a": String.valueOf(this.weight)) + String.format("Color: %s", this.color == null ? "n/a" : this.color);
    }
}

package Google;

public class Car {
    private String carModel;
    private int carSpeed;

    public Car() {

    }


    public Car (String carModel, int carSpeed) {
        this.carModel = carModel;
        this.carSpeed = carSpeed;
    }
    public String getCar () {
        if (carModel != null) {
            return String.format("Car:%n%s %d", this.carModel, this.carSpeed);
        } else {
            return "Car:";
        }
    }
}

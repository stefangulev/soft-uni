package parking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parking {
    private List<Car> data;
    private String type;
    private int capacity;

    public Parking (String type, int capacity) {
        this.type = type;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add (Car car) {
        if (this.data.size() < this.capacity) {
            this.data.add(car);
        }
    }

    public boolean remove(String manufacturer, String model) {
        return this.data.removeIf(e -> e.getManufacturer().equals(manufacturer) && e.getModel().equals(model));
    }

    public Car getLatestCar() {
        Car car = null;

        int latestYear = Integer.MIN_VALUE;

        if (!data.isEmpty()) {
            for (Car datum : data) {
                if (datum.getYear() > latestYear) {
                    latestYear = datum.getYear();
                    car = datum;
                }
            }
        }
        return car;
    }

    public Car getCar(String manufacturer, String model) {
        Car car = null;

        for (Car datum : data) {
            if (datum.getManufacturer().equals(manufacturer) && datum.getModel().equals(model)) {
                car = datum;
            }
        }

        return car;
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        return String.format("The cars are parked in %s:%n", this.type) +
                this.data.stream().map(String::valueOf).collect(Collectors.joining(System.lineSeparator()));

    }
}

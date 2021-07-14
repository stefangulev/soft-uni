package christmas;

import java.util.ArrayList;
import java.util.List;

public class Bag {
    private String color;
    private int capacity;
    private List<Present> data;

    public Bag (String color, int capacity) {
        this.color = color;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getColor() {
        return this.color;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int count() {
        return this.data.size();
    }

    public void add (Present present) {
        if (this.capacity > this.data.size()) {
            this.data.add(present);
        }
    }

    public boolean remove (String name) {
        return this.data.removeIf(present -> present.getName().equals(name));
    }

    public Present heaviestPresent() {
        double largestWeight = Double.MIN_VALUE;
        Present heaviest = null;
        for (Present datum : data) {
            if (datum.getWeight() > largestWeight) {
                heaviest = new Present(datum.getName(), datum.getWeight(), datum.getGender());
            }
        }
        return heaviest;
    }

    public Present getPresent(String name) {
        Present present = null;
        for (Present datum : data) {
            if (datum.getName().equals(name)) {
                present = new Present(datum.getName(), datum.getWeight(), datum.getGender());
            }
        }
        return present;
    }

    public String report() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("%s bag contains:%n", this.color));
        for (Present datum : data) {
            result.append(datum).append(System.lineSeparator());
        }
     return result.toString().trim();
    }


}

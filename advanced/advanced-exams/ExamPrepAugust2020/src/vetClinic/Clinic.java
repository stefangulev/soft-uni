package vetClinic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Clinic {
    private List<Pet> data;
    private int capacity;

    public Clinic(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if (this.data.size() < this.capacity) {
            data.add(pet);
        }
    }

    public boolean remove (String name) {
        return data.removeIf(pet -> pet.getName().equals(name));
    }

    public Pet getPet(String name, String owner) {
        Pet pet = null;

        for (Pet datum : data) {
            if (datum.getName().equals(name) && datum.getOwner().equals(owner)) {
                pet = datum;
            }
        }

        return pet;
    }

    public Pet getOldestPet() {
        int oldestAge = Integer.MIN_VALUE;
        Pet pet = null;
        for (Pet datum : data) {
            if (datum.getAge() > oldestAge) {
                oldestAge = datum.getAge();
                pet = datum;
            }
        }
        return pet;
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        String result = String.format("The clinic has the following patients:%n");
        result += data.stream().map(e -> String.valueOf(e.getName()) + " " + String.valueOf(e.getOwner())).collect(Collectors.joining(System.lineSeparator()));

        return result;
    }

}

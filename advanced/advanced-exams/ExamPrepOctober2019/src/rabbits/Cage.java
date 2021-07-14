package rabbits;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cage {
    private String name;
    private int capacity;
    private List<Rabbit> data;

    public Cage (String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void add(Rabbit rabbit) {
        if (this.data.size() < this.capacity) {
            this.data.add(rabbit);
        }
    }

    public boolean removeRabbit(String name) {
        return this.data.removeIf(rabbit -> rabbit.getName().equals(name));
    }

    public void removeSpecies(String species) {
        this.data.removeIf(rabbit -> rabbit.getSpecies().equals(species));
    }

    public Rabbit sellRabbit(String name) {
        Rabbit rabbit = null;
        for (Rabbit r : this.data ) {
            if (r.getName().equals(name)) {
                r.setAvailable();
                rabbit = r;
                break;
            }
        }
        return rabbit;
    }

    public List<Rabbit> sellRabbitBySpecies(String species) {
        this.data.stream().forEach(rabbit -> {
            if (rabbit.getSpecies().equals(species)) {
                rabbit.setAvailable();
            }
        });

        return this.data.stream().filter(rabbit -> !rabbit.isAvailable()).collect(Collectors.toList());

    }

    public int count() {
        return this.data.size();
    }

    public String report() {
      return String.format("Rabbits available at %s:%n", this.name) + this.data.stream().filter(Rabbit::isAvailable)
                .map(String::valueOf).collect(Collectors.joining(System.lineSeparator()));
    }
}

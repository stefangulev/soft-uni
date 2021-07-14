import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String full_name;

    @ManyToMany(mappedBy = "drivers")
    Set<Truck> trucks = new LinkedHashSet<>();

    public void addTruck(Truck truck) {
        this.trucks.add(truck);
    }
    public Driver() {

    }
    public Driver(String full_name) {
        this.full_name = full_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }
}

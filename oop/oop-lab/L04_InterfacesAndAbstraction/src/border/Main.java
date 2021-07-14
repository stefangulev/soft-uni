package border;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Citizen citizen = new Citizen("ivan", 13, "levski");
        Robot robot = new Robot("ivan", "levski");

        List<Identifiable> list = new ArrayList<>();

        list.add(citizen);
        list.add(robot);

    }
}

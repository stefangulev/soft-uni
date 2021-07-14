package CarSalesman;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        Map<String, Engine> engines = new LinkedHashMap<>();

        for (int i = 0; i <n ; i++) {
            String[] tokens = scan.nextLine().split("\\s+");
            String engineName = tokens[0];
            int power = Integer.parseInt(tokens[1]);
            Engine engine;
            if (tokens.length == 4) {

                int displacement = Integer.parseInt(tokens[2]);
                String efficiency = tokens[3];
                engine = new Engine(engineName, power, displacement, efficiency);

            } else if (tokens.length == 2) {
                engine = new Engine(engineName, power);
            } else  {
                try {
                    int displacement = Integer.parseInt(tokens[2]);
                    engine = new Engine(engineName, power, displacement);
                } catch (NumberFormatException ex) {
                    String efficiency = tokens[2];
                    engine = new Engine(engineName, power, efficiency);
                }

            }

            engines.put(engine.getModel(), engine);
        }

        int m = Integer.parseInt(scan.nextLine());
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i <m ; i++) {
            String[] tokens = scan.nextLine().split("\\s+");
            String model = tokens[0];
            String engineModel = tokens[1];
            Engine engine = new Engine(engineModel, engines.get(engineModel).getPower(), engines.get(engineModel).getDisplacement(),
                    engines.get(engineModel).getEfficiency());

            Car car;
            if (tokens.length == 4) {
                int weight = Integer.parseInt(tokens[2]);
                String color = tokens[3];

                car = new Car(model, engine, weight, color);
            } else if (tokens.length == 2) {
                car = new Car (model, engine);
            } else {
                try {
                    int weight = Integer.parseInt(tokens[2]);
                    car = new Car (model, engine, weight);
                } catch (NumberFormatException ex) {
                    String color = tokens[2];
                    car = new Car(model, engine,  color);
                }
            }
            cars.add(car);

        }
        for (Car car : cars) {
            System.out.println(car);
        }
    }
}

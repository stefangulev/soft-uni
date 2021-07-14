package SpeedRacing;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        Map<String, Car> cars = new LinkedHashMap<>();

        while (n-- > 0 ) {
            String[] tokens = scan.nextLine().split("\\s+");
            String model = tokens[0];
            double fuelAmount = Double.parseDouble(tokens[1]);
            double fuelCostPerKm = Double.parseDouble(tokens[2]);
            Car car = new Car(model, fuelAmount, fuelCostPerKm);
            cars.put(car.getModel(), car);
        }

        String input = scan.nextLine();
        while (!input.equals("End")) {
            String[] tokens = input.split("\\s+");
            String model = tokens[1];
            int distanceToTravel = Integer.parseInt(tokens[2]);

            cars.get(model).driveCar(distanceToTravel);

            input = scan.nextLine();
        }

        for (Car value : cars.values()) {
            System.out.println(value);
        }




    }
}

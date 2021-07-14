package vehicle;

import java.util.Scanner;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] carInfo = scan.nextLine().split("\\s+");
        String[] truckInfo = scan.nextLine().split("\\s+");
        String[] busInfo = scan.nextLine().split("\\s+");

        Car car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]), Double.parseDouble(carInfo[3]));
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]), Double.parseDouble(truckInfo[3]));
        Bus bus = new Bus(Double.parseDouble(busInfo[1]), Double.parseDouble(busInfo[2]), Double.parseDouble(busInfo[3]));

        int n = Integer.parseInt(scan.nextLine());

        while (n-- > 0) {
            String[] tokens = scan.nextLine().split("\\s+");

            double value = Double.parseDouble(tokens[2]);

            if (tokens[0].equals("Drive") || tokens[0].equals("DriveEmpty")) {
                if (tokens[1].equals("Car")) {
                    System.out.println(car.drive(value));
                } else if (tokens[1].equals("Truck")) {
                    System.out.println(truck.drive(value));
                } else {
                    System.out.println(bus.drive(value, tokens[0]));
                }
            } else if (tokens[0].equals("Refuel")) {
                if (tokens[1].equals("Car")) {
                    car.refuel(value);
                } else if (tokens[1].equals("Truck")) {
                    truck.refuel(value);
                } else {
                    bus.refuel(value);
                }
            }
        }
        System.out.println(car);
        System.out.println(truck);
        System.out.println(bus);

    }
}

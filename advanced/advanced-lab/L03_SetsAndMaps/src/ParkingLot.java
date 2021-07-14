import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class ParkingLot {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        Set<String> parkingLot = new LinkedHashSet<>();

        while (!input.equals("END")) {
            String[] tokens = input.split(", ");

            String command = tokens[0];
            String licensePlate = tokens[1];

            if (command.equals("IN")) {
                parkingLot.add(licensePlate);
            } else if (command.equals("OUT")) {
                parkingLot.remove(licensePlate);
            }

            input = scan.nextLine();
        }


        if (parkingLot.isEmpty()) {
            System.out.println("Parking Lot is Empty");
        } else {
            parkingLot.forEach(e -> System.out.println(e));
        }

    }
}

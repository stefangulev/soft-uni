package TrafficLights;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TrafficLight[] trafficLights =
                Arrays.stream(scan.nextLine().split("\\s+")).map(TrafficLight::valueOf).toArray(TrafficLight[]::new);

        int n = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < n; i++) {
             trafficLights = TrafficLight.lightSwitcher(trafficLights);
             trafficLightPrinter(trafficLights);

        }


    }
    public static void trafficLightPrinter(TrafficLight[] trafficLights) {
        for (TrafficLight trafficLight : trafficLights) {
            System.out.print(trafficLight.name() + " ");
        }
        System.out.println();
    }
}

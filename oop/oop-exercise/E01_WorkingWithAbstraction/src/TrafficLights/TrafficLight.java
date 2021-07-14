package TrafficLights;

public enum TrafficLight {
    RED,
    GREEN,
    YELLOW;


    public static TrafficLight[] lightSwitcher(TrafficLight[] arr) {

        TrafficLight[] updated = new TrafficLight[arr.length];
        for (int i = 0; i < arr.length; i++) {

            if (arr[i].equals(TrafficLight.valueOf("GREEN"))) {
                updated[i] = TrafficLight.valueOf("YELLOW");
            } else if (arr[i].equals(TrafficLight.valueOf("RED"))) {
                updated[i] = TrafficLight.valueOf("GREEN");
            } else if (arr[i].equals(TrafficLight.valueOf("YELLOW"))) {
                updated[i] = TrafficLight.valueOf("RED");
            }
        }
        return updated;
    }


}

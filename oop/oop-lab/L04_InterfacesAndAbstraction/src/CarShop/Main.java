package CarShop;

public class Main {
    public static void main(String[] args) {
        Car seat = new Seat("Leon", "Gray", 110, "Spain", 11111.1);
        Car seat1 = new Seat("A4", "Gray", 110, "Germany", 3.0);

        printCarInfo(seat);
        printCarInfo(seat1);
    }

    private static void printCarInfo(Car car) {
        System.out.println(String.format(
                "%s is %s color and have %s horse power",
                car.getModel(),
                car.getColor(),
                car.getHorsePower()));
        System.out.println(car.toString());
    }



}

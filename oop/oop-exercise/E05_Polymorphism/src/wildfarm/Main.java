package wildfarm;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        List<Animal> animals = new ArrayList<>();

        while (!input.equals("End")) {
            String[] objectInfo = input.split("\\s+");
            String[] foodInfo = scan.nextLine().split("\\s+");

            Food food;
            if (foodInfo[0].equals("Vegetable")) {
                food = new Vegetable(Integer.parseInt(foodInfo[1]));
            } else {
                food = new Meat(Integer.parseInt(foodInfo[1]));
            }

            Animal animal;
            switch (objectInfo[0]) {
                case "Cat":
                    animal = new Cat(objectInfo[0], objectInfo[1], Double.parseDouble(objectInfo[2]), objectInfo[3], objectInfo[4]);
                    break;
                case "Tiger":
                    animal = new Tiger(objectInfo[0], objectInfo[1],Double.parseDouble(objectInfo[2]), objectInfo[3]);
                    break;
                case "Zebra":
                    animal = new Zebra(objectInfo[0], objectInfo[1],Double.parseDouble(objectInfo[2]), objectInfo[3]);
                    break;
                default:
                    animal = new Mouse(objectInfo[0], objectInfo[1],Double.parseDouble(objectInfo[2]), objectInfo[3]);
                    break;
            }
            animal.eatFood(food);
            animals.add(animal);

            input = scan.nextLine();
        }

        animals.forEach(System.out::println);


    }
}

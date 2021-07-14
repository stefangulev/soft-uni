package hierarchy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        List<Animal> list = new ArrayList<>();

        while (!input.equals("Beast!")) {
            String[] tokens = scan.nextLine().split("\\s+");

            try {
                Animal animal = createAnimal(input, tokens);
                list.add(animal);
            } catch(IllegalStateException ex) {
                System.out.println(ex.getMessage());
            }

            input = scan.nextLine();



        }

        for (Animal animal : list) {
            System.out.println(animal);
        }


    }

    public static Animal createAnimal(String type, String[] tokens) {


        if (tokens[0].equals("") || tokens[1].equals("") || tokens[2].equals("")) {
            throw new IllegalStateException("Invalid input!");
        }
            String name = tokens[0];
        int age = Integer.parseInt(tokens[1]);
        String gender = tokens[2];

        if (age < 0) {
            throw new IllegalStateException("Invalid input!");
        }


        Animal animal;
        switch (type) {
            case "Cat":
                animal = new Cat(name, age, gender);
                break;
            case "Dog":
                animal = new Dog (name, age, gender);
                break;
            case "Frog":
                animal = new Frog (name, age, gender);
                break;
            case "Kitten":
                animal = new Kitten (name, age, gender);
                break;
            case "Tomcat":
                animal = new Tomcat (name, age, gender);
                break;
            default: throw new IllegalStateException("Invalid input!");

        }
        return animal;
    }
}

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainComparingObjects {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        List<PersonComparingObjects> personList = new ArrayList<>();

        while (!input.equals("END")) {
            String[] tokens = input.split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            String town = tokens[2];
            PersonComparingObjects person = new PersonComparingObjects(name, age, town);
            personList.add(person);

            input = scan.nextLine();
        }

        int n = Integer.parseInt(scan.nextLine());

        PersonComparingObjects personToCompare = personList.get(n - 1);

        int countEqual = 0;
        int countNonEqual = 0;

        for (PersonComparingObjects person : personList) {
            if (personToCompare.compareTo(person) == 0) {
                countEqual++;
            } else {
                countNonEqual++;
            }
        }

        if (countEqual == 1) {
            countEqual--;
        }

        if (countEqual > 0) {
            System.out.printf("%d %d %d", countEqual , countNonEqual , personList.size());
        } else {
            System.out.println("No matches");
        }
    }
}

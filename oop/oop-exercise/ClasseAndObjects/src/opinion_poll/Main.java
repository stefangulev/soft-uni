package opinion_poll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Person> list = new ArrayList<>();

        int n = Integer.parseInt(scan.nextLine());

        for (int i = 0; i <n ; i++) {
            String[] input = scan.nextLine().split(" ");
            String name = input[0];
            int age = Integer.parseInt(input[1]);

            if (age > 30) {
                Person person = new Person(name, age);
                list.add(person);
            }
        }

        list.stream().sorted((p1, p2) -> p1.getName().compareTo(p2.getName())).forEach(e -> System.out.println(e));

    }
}

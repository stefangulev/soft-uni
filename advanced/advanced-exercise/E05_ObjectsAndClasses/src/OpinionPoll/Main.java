package OpinionPoll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        List<Person> personList = new ArrayList<>();

        for (int i = 0; i <n ; i++) {
            String[] tokens = scan.nextLine().split("\\s+");

            Person person = new Person(tokens[0], Integer.parseInt(tokens[1]));
            personList.add(person);

        }

        personList.stream().filter(e -> e.getAge() > 30)
                .sorted((l, r) -> l.getName().compareTo(r.getName()))
                .forEach(e -> System.out.println(String.format("%s - %d", e.getName(), e.getAge())));

    }
}

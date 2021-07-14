import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class MainIDK {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        TreeSet<Person> firstSet = new TreeSet<>(new NameComparator());
        TreeSet<Person> secondSet = new TreeSet<>(new AgeComparator());

        int n = Integer.parseInt(scan.nextLine());

        while (n-- > 0) {
            String[] tokens = scan.nextLine().split("\\s+");
            String name = tokens[0];
            int age = Integer.parseInt(tokens[1]);
            Person person = new Person(name ,age);
            firstSet.add(person);
            secondSet.add(person);
        }

        for (Person person : firstSet) {
            System.out.println(person.getName() + " " + person.getAge());
        }

        for (Person person : secondSet) {
            System.out.println(person.getName() + " " + person.getAge());
        }


    }
}

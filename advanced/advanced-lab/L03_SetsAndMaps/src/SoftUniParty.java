import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SoftUniParty {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Set<String> vipList = new TreeSet<>();
        Set<String> regularList = new TreeSet<>();
        String input = scan.nextLine();
        while (!input.equals("PARTY")) {
            if (input.length() == 8 && Character.isDigit(input.charAt(0))) {
                vipList.add(input);
            } else if (input.length() == 8 && !Character.isDigit(input.charAt(0))) {
                regularList.add(input);
            }

            input = scan.nextLine();
        }
        String secondInput = scan.nextLine();

        while (!secondInput.equals("END")) {
            vipList.remove(secondInput);
            regularList.remove(secondInput);

            secondInput = scan.nextLine();
        }
        System.out.println(vipList.size() + regularList.size());
        if (!vipList.isEmpty()) {
            System.out.println(String.join(System.lineSeparator(), vipList));
        }
        if (!regularList.isEmpty()) {
            System.out.println(String.join(System.lineSeparator(), regularList));
        }
    }
}

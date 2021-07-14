import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateParty {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<String> initialGuestList = Arrays.stream(scan.nextLine().split("\\s+")).collect(Collectors.toList());

        List<String> updatedList = new ArrayList<>(initialGuestList);


        String input = scan.nextLine();

        BiPredicate<String, String> nameStart = String::startsWith;
        BiPredicate<String, String> nameEnd = String::endsWith;
        BiPredicate<String, Integer> nameLength = (str ,i ) -> str.length() == i;


        while (!input.equals("Party!")) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            if (command.equals("Double")) {
                switch (tokens[1]) {
                    case "StartsWith":
                        String startSequence = tokens[2];
                        initialGuestList.forEach(e -> {
                            if (nameStart.test(e , startSequence)) {
                                updatedList.add(updatedList.indexOf(e), e);
                                updatedList.add(updatedList.indexOf(e), e);
                            }
                        });
                        break;
                    case "EndsWith":
                        String endSequence = tokens[2];
                        initialGuestList.forEach(e -> {
                            if (nameEnd.test(e , endSequence)) {
                                updatedList.add(updatedList.indexOf(e), e);
                                updatedList.add(updatedList.indexOf(e), e);
                            }
                        });
                        break;
                    case "Length":
                        int length = Integer.parseInt(tokens[2]);
                        initialGuestList.forEach(e -> {
                            if (nameLength.test(e, length)) {
                                updatedList.add(updatedList.indexOf(e), e);
                                updatedList.add(updatedList.indexOf(e), e);
                            }
                        });
                        break;
                }

            } else if (command.equals("Remove")) {


            }

            input = scan.nextLine();
        }

        System.out.println();
    }
}

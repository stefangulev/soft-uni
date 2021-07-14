import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardsGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> firstDeck = Arrays.stream(scan.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> secondDeck = Arrays.stream(scan.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        while (firstDeck.size() > 0 && secondDeck.size() > 0) {

            if(firstDeck.get(0) > secondDeck.get(0)) {
                firstDeck.add(firstDeck.get(0));
                firstDeck.remove(0);
                firstDeck.add(secondDeck.get(0));
                secondDeck.remove(0);
            } else if (secondDeck.get(0) > firstDeck.get(0)){
                secondDeck.add(secondDeck.get(0));
                secondDeck.remove(0);
                secondDeck.add(firstDeck.get(0));
                firstDeck.remove(0);
            } else if (firstDeck.get(0).equals(secondDeck.get(0))) {
                firstDeck.remove(0);
                secondDeck.remove(0);
            }
        }

        int sum = 0;
        if (firstDeck.size() > secondDeck.size()) {
            for (Integer integer : firstDeck) {
                sum +=integer;
            }
            System.out.printf("First player wins! Sum: %d", sum);

        } else if (secondDeck.size() > firstDeck.size()) {
            for (Integer integer : secondDeck) {
                sum += integer;
            }
            System.out.printf("Second player wins! Sum: %d", sum);
        }
    }
}

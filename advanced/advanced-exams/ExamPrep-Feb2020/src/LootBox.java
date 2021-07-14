import java.util.*;
import java.util.stream.Collectors;

public class LootBox {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> firstLootBox = Arrays.stream(scan.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());

        ArrayDeque<Integer> firstBox = new ArrayDeque<>();


        for (Integer lootBox : firstLootBox) {
            firstBox.offer(lootBox);
        }

        ArrayDeque<Integer> secondBox = new ArrayDeque<>();

        List<Integer> secondLootBox = Arrays.stream(scan.nextLine().split("\\s+"))
                .map(Integer::parseInt).collect(Collectors.toList());

        for (Integer lootBox : secondLootBox) {
            secondBox.push(lootBox);
        }

        int totalLoot = 0;

        while (!firstBox.isEmpty() && !secondBox.isEmpty()) {

            if ((firstBox.peek() + secondBox.peek()) % 2 == 0) {
                totalLoot += firstBox.poll() + secondBox.pop();
            } else {
                firstBox.offer(secondBox.pop());
            }

        }

        if (firstBox.isEmpty()) {
            System.out.println("First lootbox is empty");
        } else {
            System.out.println("Second lootbox is empty");
        }

        if (totalLoot >= 100) {
            System.out.printf("Your loot was epic! Value: %d", totalLoot);
        } else {
            System.out.printf("Your loot was poor... Value: %d", totalLoot);
        }
    }
}

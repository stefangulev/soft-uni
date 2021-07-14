import java.util.*;

public class SantaPresentFactory {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] materialsValues = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] magicValues = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();


        Deque<Integer> materialStack = new ArrayDeque<>();

        for (int materialsValue : materialsValues) {
            materialStack.push(materialsValue);
        }

        Deque<Integer> magicQueue = new ArrayDeque<>();

        for (int magicValue : magicValues) {
            magicQueue.offer(magicValue);
        }

        Map<String, Integer> giftList = new TreeMap<>();

        while (!materialStack.isEmpty() && !magicQueue.isEmpty()) {
            int result = materialStack.peek() * magicQueue.peek();

            if (result < 0) {
                result = materialStack.pop() + magicQueue.poll();
                materialStack.push(result);
            } else if (result == 0) {
                if (magicQueue.peek() == 0) {
                    magicQueue.poll();
                }
                if (materialStack.peek() == 0) {
                    materialStack.pop();
                }
            } else {
                if (result == 150 || result == 250 || result == 300 || result == 400) {
                    materialStack.pop();
                    magicQueue.poll();
                    String gift = "";
                    switch (result) {
                        case 150:
                            gift = "Doll";
                            break;
                        case 250:
                            gift = "Wooden train";
                            break;
                        case 300:
                            gift = "Teddy bear";
                            break;
                        case 400:
                            gift = "Bicycle";
                            break;
                    }
                    giftList.putIfAbsent(gift, 0);
                    giftList.put(gift, giftList.get(gift) + 1);
                } else {
                    magicQueue.poll();
                    materialStack.push(materialStack.pop() + 15);
                }
            }
        }

        if ((giftList.containsKey("Doll") && giftList.containsKey("Wooden train")) ||
                (giftList.containsKey("Teddy bear") && giftList.containsKey("Bicycle"))) {
            System.out.println("The presents are crafted! Merry Christmas!");
        } else {
            System.out.println("No presents this Christmas!");
        }

        if (!materialStack.isEmpty()) {
            System.out.println(String.format("Materials left: %s", materialStack.toString().replaceAll("[\\[\\]]", "")));
        }

        if (!magicQueue.isEmpty()) {
            System.out.println(String.format("Magic left: %s", magicQueue.toString().replaceAll("[\\[\\]]", "")));
        }

        giftList.entrySet().forEach(e -> System.out.println(String.format("%s: %d", e.getKey(), e.getValue())));

    }
}

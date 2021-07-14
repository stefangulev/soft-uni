import java.util.*;
import java.util.stream.Collectors;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] lilies = Arrays.stream(scan.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int[] roses = Arrays.stream(scan.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> liliesStack = new ArrayDeque<>();

        Arrays.stream(lilies).forEach(liliesStack::push);

        ArrayDeque<Integer> rosesQueue = new ArrayDeque<>();

        Arrays.stream(roses).forEach(rosesQueue::offer);

        ArrayDeque<Integer> storedForLater = new ArrayDeque<>();

        int wreathCount = 0;

        while (!liliesStack.isEmpty() && !rosesQueue.isEmpty()) {
            int result = liliesStack.peek() + rosesQueue.peek();

            if (result == 15) {
                wreathCount++;
                liliesStack.pop();
                rosesQueue.poll();
            } else if (result > 15) {
                liliesStack.push(liliesStack.pop() - 2);
            } else {
                storedForLater.push(liliesStack.pop());
                storedForLater.push(rosesQueue.poll());
            }
        }
        int storedForLaterSum = 0;
        while (!storedForLater.isEmpty()) {
            storedForLaterSum += storedForLater.pop();
            if (storedForLaterSum >= 15) {
                wreathCount++;
                storedForLaterSum -= 15;
            }
        }

        if (wreathCount < 5) {
            System.out.println(String.format("You didn't make it, you need %d wreaths more!", 5 - wreathCount));
        } else {
            System.out.println(String.format("You made it, you are going to the competition with %d wreaths!", wreathCount));
        }




    }
}

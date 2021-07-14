import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DatingApp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] males = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] females = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> maleStack = new ArrayDeque<>();

        Arrays.stream(males).forEach(maleStack::push);

        ArrayDeque<Integer> femaleQueue = new ArrayDeque<>();

        Arrays.stream(females).forEach(femaleQueue::offer);

        int matchesCount = 0;

        while (!maleStack.isEmpty() && !femaleQueue.isEmpty()) {

            while (maleStack.peek() <= 0) {
                maleStack.pop();
                if (!dequeStatusCheck(maleStack, femaleQueue)) {
                    break;
                }
            }
            if (!dequeStatusCheck(maleStack, femaleQueue)) {
                break;
            }

            while (femaleQueue.peek() <= 0) {
               femaleQueue.poll();
                if (!dequeStatusCheck(maleStack, femaleQueue)) {
                    break;
                }
            }
            if (!dequeStatusCheck(maleStack, femaleQueue)) {
                break;
            }

            while (maleStack.peek() % 25 == 0) {
                maleStack.pop();
                if (!dequeStatusCheck(maleStack, femaleQueue)) {
                    break;
                }
                maleStack.pop();
                if (!dequeStatusCheck(maleStack, femaleQueue)) {
                    break;
                }
            }
            if (!dequeStatusCheck(maleStack, femaleQueue)) {
                break;
            }
            while (femaleQueue.peek() % 25 == 0) {
                femaleQueue.poll();
                if (!dequeStatusCheck(maleStack, femaleQueue)) {
                    break;
                }
                femaleQueue.poll();
                if (!dequeStatusCheck(maleStack, femaleQueue)) {
                    break;
                }
            }
            if (!dequeStatusCheck(maleStack, femaleQueue)) {
                break;
            }

            if (maleStack.peek().equals(femaleQueue.peek())) {
                matchesCount++;
                maleStack.pop();
                femaleQueue.poll();
                if (!dequeStatusCheck(maleStack, femaleQueue)) {
                    break;
                }

            } else {
                maleStack.push(maleStack.pop() - 2);
                femaleQueue.poll();

            }


        }
        System.out.println(String.format("Matches: %d", matchesCount));

        System.out.println(String.format("Males left: %s", maleStack.isEmpty() ? "none" : maleStack.stream().map(String::valueOf).collect(Collectors.joining(", "))));
        System.out.println(String.format("Females left: %s", femaleQueue.isEmpty() ? "none" : femaleQueue.stream().map(String::valueOf).collect(Collectors.joining(", "))));


    }

    public static boolean dequeStatusCheck(ArrayDeque<Integer> male, ArrayDeque<Integer> female) {
        return !male.isEmpty() && !female.isEmpty();
    }
}

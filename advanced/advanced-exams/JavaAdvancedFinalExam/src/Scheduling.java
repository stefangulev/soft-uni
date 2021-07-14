import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Scheduling {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] tasks = Arrays.stream(scan.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> tasksStack = new ArrayDeque<>();

        Arrays.stream(tasks).forEach(tasksStack::push);

        int[] threads = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        ArrayDeque<Integer> threadsQueue = new ArrayDeque<>();

        Arrays.stream(threads).forEach(threadsQueue::offer);

        int taskToKill = Integer.parseInt(scan.nextLine());

        while (true) {

            int threadValue = threadsQueue.poll();
            int taskValue = tasksStack.pop();

            if (taskValue == taskToKill) {
                threadsQueue.offerFirst(threadValue);
                System.out.println(String.format("Thread with value %d killed task %d", threadValue, taskValue));
                break;
            } else {

                if (threadValue < taskValue) {
                    tasksStack.push(taskValue);
                }

            }
        }
        threadsQueue.stream().forEach(thread -> System.out.print(thread + " "));

    }
}

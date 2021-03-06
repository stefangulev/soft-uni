import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.function.Function;

public class CustomMinFunction {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] nums = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();


        Function<int[], Integer> minFunc = x -> {
            int smallestNumber = Integer.MAX_VALUE;
            for (Integer integer : x) {
                if (smallestNumber > integer) {
                    smallestNumber = integer;
                }
            }
            return smallestNumber;
        };

        System.out.println(minFunc.apply(Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray()));
    }
}

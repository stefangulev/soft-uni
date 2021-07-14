import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class AppliedArithmetics {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] nums = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Function<int[], int[]> add = x -> {
            for (int i = 0; i < x.length; i++) {
                x[i] += 1;
            }
            return x;
        };

        Function<int[], int[]> multiply = x -> {
            for (int i = 0; i < x.length; i++) {
                x[i] *= 2;
            }
            return x;
        };

        Function<int[], int[]> subtract = x -> {
            for (int i = 0; i < x.length; i++) {
                x[i] -= 1;
            }
            return x;
        };

        Consumer<int[]> print = x -> {
            for (int i = 0; i < x.length ; i++) {
                System.out.print(x[i] + " ");
            }
            System.out.println();
        };

        String input = scan.nextLine();

        while (!input.equals("end")) {

            switch (input) {
                case "add":
                    nums = add.apply(nums);
                    break;
                case "multiply":
                    nums = multiply.apply(nums);
                    break;
                case "subtract":
                    nums = subtract.apply(nums);
                    break;
                case "print":
                    print.accept(nums);
                    break;
            }

            input = scan.nextLine();
        }
    }
}

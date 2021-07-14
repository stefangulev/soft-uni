import java.util.Arrays;
import java.util.Scanner;

public class SumEvenNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] numbers = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int evenSum = 0;
        for (int number : numbers) {
            if (number % 2 == 0) {
                evenSum +=number;
            }

        }
        System.out.println(evenSum);
    }
}

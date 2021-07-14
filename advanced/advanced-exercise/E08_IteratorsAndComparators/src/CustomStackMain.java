import java.util.Arrays;
import java.util.Scanner;

public class CustomStackMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        CustomStack stack = new CustomStack();


        String input = scan.nextLine();

        while (!input.equals("END")) {
            input = input.replace(",", "");
            String[] tokens = input.split("\\s+");
            String command = tokens[0];

            if (command.equals("Push")) {
                String[] elements = Arrays.copyOfRange(tokens, 1, tokens.length);
                int[] nums = Arrays.stream(elements).mapToInt(Integer::parseInt).toArray();
                stack.push(nums);
            } else if (command.equals("Pop")) {
                stack.pop();
            }


            input = scan.nextLine();
        }

        for (int i = 0; i < 2 ; i++) {
            for (Integer integer : stack) {
                System.out.println(integer);
            }
        }

    }
}

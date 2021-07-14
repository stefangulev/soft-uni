import java.util.Random;
import java.util.Scanner;

public class RandomizeWordd {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split(" ");

        Random rnd = new Random();

        for (int i = 0; i < input.length; i++) {

            int pos2 = rnd.nextInt(input.length);

            String currentIndex = input[i];

            input[i] = input[pos2];
            input[pos2] = currentIndex;
        }
        for (String s : input) {
            System.out.println(s);


        }
    }
}

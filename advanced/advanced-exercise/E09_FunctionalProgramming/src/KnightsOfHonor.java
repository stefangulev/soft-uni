import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class KnightsOfHonor {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Consumer<String> editor = x -> System.out.println(x);
        Arrays.stream(scan.nextLine().split("\\s+")).map(e -> "Sir" + " " + e).forEach(editor);
    }
}

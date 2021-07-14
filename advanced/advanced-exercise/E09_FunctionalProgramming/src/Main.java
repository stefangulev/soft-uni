import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] tokens = scan.nextLine().split("\\s+");

        Consumer<String> printer = System.out::println;

        for (String token : tokens) {
            printer.accept(token);
        }
    }
}

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class CustomComparator {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Arrays.stream(scan.nextLine().split("\\s+"))
                .map(Integer::parseInt).sorted(Comparator.comparingInt((Integer l) -> Math.abs(l % 2)).thenComparingInt(l -> l))
                .map(e -> e + " ").forEach(System.out::print);




    }


}

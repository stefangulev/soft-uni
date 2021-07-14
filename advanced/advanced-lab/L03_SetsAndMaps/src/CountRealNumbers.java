import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CountRealNumbers {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<Double, Integer> numbers = new LinkedHashMap<>();

                Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToDouble(Double::parseDouble).forEach(e -> {
                    numbers.putIfAbsent(e, 0);
                    numbers.put(e, numbers.get(e) + 1);

                });

        //numbers.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue()));
        String output = numbers.entrySet().stream()
                .map(entry -> String.format("%.1f -> %d", entry.getKey(), entry.getValue())).
                collect(Collectors.joining(System.lineSeparator()));

        System.out.println(output);




    }
}

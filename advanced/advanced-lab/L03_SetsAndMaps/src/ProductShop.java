import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ProductShop {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, LinkedHashMap<String, Double>> shops = new TreeMap<>();

        String input = scan.nextLine();

        while (!input.equals("Revision")) {
            String[] tokens = input.split(", ");
            String shop = tokens[0];
            String product = tokens[1];
            double price = Double.parseDouble(tokens[2]);

            shops.putIfAbsent(shop, new LinkedHashMap<>());
            shops.get(shop).put(product, price);


            input = scan.nextLine();
        }

        shops.entrySet().stream().forEach(e -> {
            System.out.println(e.getKey() + "->");
            Map<String, Double> current = e.getValue();
            current.entrySet().stream().
                    forEach(entry -> System.out.println(String.format("Product: %s, Price: %.1f", entry.getKey(), entry.getValue())));
        });
    }
}

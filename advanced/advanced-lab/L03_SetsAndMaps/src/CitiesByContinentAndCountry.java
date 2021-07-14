import java.util.*;

public class CitiesByContinentAndCountry {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        LinkedHashMap<String, Map<String, List<String>>> entries = new LinkedHashMap<>();

        for (int i = 0; i <n ; i++) {
            String input[] = scan.nextLine().split("\\s+");
            String continent = input[0];
            String country =input[1];
            String city = input[2];

            entries.putIfAbsent(continent, new LinkedHashMap<>());
            entries.get(continent).putIfAbsent(country, new ArrayList<>());
            entries.get(continent).get(country).add(city);

        }

        entries.entrySet().stream().forEach(e -> {
            System.out.println(e.getKey() + ":");
            e.getValue().entrySet().stream().forEach(entry -> {
                System.out.println(String.format("  %s -> %s", entry.getKey(), entry.getValue().toString().replaceAll("[\\[\\]]", "")));
            });
        });
    }
}

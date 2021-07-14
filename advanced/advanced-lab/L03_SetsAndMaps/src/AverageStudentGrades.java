import java.util.*;

public class AverageStudentGrades {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Map<String, List<Double>> schoolRecord = new TreeMap<>();

        int n = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < n ; i++) {
            String[] input = scan.nextLine().split("\\s+");
            String name = input[0];
            double grade = Double.parseDouble(input[1]);

            schoolRecord.putIfAbsent(name, new ArrayList<>());
            schoolRecord.get(name).add(grade);
        }

        schoolRecord.entrySet().stream().forEach(entry -> {
            System.out.print(entry.getKey() + " -> ");
            for (Double aDouble : entry.getValue()) {
                System.out.printf("%.2f ", aDouble);
            }
            System.out.printf("(avg: %.2f)", entry.getValue().stream().mapToDouble(e ->e).average().getAsDouble());
            System.out.println();

        });

    }
}

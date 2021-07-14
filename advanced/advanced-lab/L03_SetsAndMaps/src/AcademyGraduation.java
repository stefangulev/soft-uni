import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class AcademyGraduation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        Map<String, List<Double>> studentList = new TreeMap<>();

        for (int i = 0; i <n ; i++) {
            String name = scan.nextLine();
            List<Double> grades = Arrays.stream(scan.nextLine().split("\\s+"))
                    .map(Double::parseDouble).collect(Collectors.toUnmodifiableList());

            studentList.putIfAbsent(name, grades);

        }
        studentList.entrySet().stream().
                forEach(e -> {

                            DecimalFormat format = new DecimalFormat("0.#####################");

                    String formatted = format.format(e.getValue().stream()
                            .mapToDouble(k -> k).average().getAsDouble());
                    System.out.println(String.format("%s is graduated with %s", e.getKey(), formatted));

                });
    }
}

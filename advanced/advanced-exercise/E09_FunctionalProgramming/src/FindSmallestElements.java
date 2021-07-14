import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindSmallestElements {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Function<List<Integer>, Integer> minFunc = x -> {
            int smallestNumber = Integer.MAX_VALUE;
            int smallestIndex = 0;
            for (int i = 0; i < x.size(); i++) {
                if (smallestNumber >= x.get(i)) {
                    smallestNumber = x.get(i);
                    smallestIndex = i;
                }
            }
            return smallestIndex;
        };


        System.out.println(minFunc.apply(Arrays.stream(scan.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList())));
    }
}

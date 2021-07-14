import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListOfPredicates {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int n = Integer.parseInt(scan.nextLine());

        BiPredicate<Integer, Integer> divisible = (l, r) -> l % r == 0;

        Set<Integer> nums = Arrays.stream(scan.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toSet());


        for (int i = 1 ; i <= n; i++) {
            boolean isDivisible = true;
            for (Integer num : nums) {
                if (!divisible.test(i, num)) {
                    isDivisible = false;
                }
            }
            if (isDivisible) {
                System.out.print(i + " ");
            }
        }

    }
}

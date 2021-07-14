import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReverseAndExclude {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<Integer> nums = Arrays.stream(scan.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        int numberToDivide = Integer.parseInt(scan.nextLine());

      Collections.reverse(nums);

        Predicate<Integer> checkIfDivisible = x -> x % numberToDivide ==0;

      Function<List<Integer>, List<Integer>> excludeDivisible = x -> {
          List<Integer> finalList = new ArrayList<>();
          for (Integer integer : x) {
              if (!checkIfDivisible.test(integer)) {
                  finalList.add(integer);
              }
          }
          return finalList;
      };

        Consumer<List<Integer>> print = x -> {
            for (Integer integer : x) {
                System.out.print(integer + " ");
            }
        };

        print.accept(excludeDivisible.apply(nums));

    }
}

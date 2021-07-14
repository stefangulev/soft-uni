package GenericIndexSwapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        List<Integer> list = new ArrayList<>();

        while (n-- > 0) {
            int input = Integer.parseInt(scan.nextLine());
            list.add(input);
        }

        String[] tokens = scan.nextLine().split("\\s+");
        int firstIndex = Integer.parseInt(tokens[0]);
        int secondIndex = Integer.parseInt(tokens[1]);

        swapIndexes(list, firstIndex, secondIndex);

    }


    public static <T> void swapIndexes (List<T> list, int firstIndex, int secondIndex) {
        T firstItem = list.get(firstIndex);
        T secondItem = list.get(secondIndex);

        list.add(secondIndex, firstItem);
        list.remove(secondIndex + 1);
        list.add(firstIndex, secondItem);
        list.remove(firstIndex + 1);

        for (T t : list) {
                System.out.println(t.getClass().getName() + ": " + t);

        }
    }
}

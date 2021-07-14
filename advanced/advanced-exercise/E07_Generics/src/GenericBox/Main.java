package GenericBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        List<GenericBox<Double>> list = new ArrayList<>();


        while (n-- != 0) {
            Double input = Double.parseDouble(scan.nextLine());
            GenericBox<Double> box = new GenericBox<>(input);
            list.add(box);
        }

       Double elementToCompare = Double.parseDouble(scan.nextLine());
        GenericBox<Double> boxToCompare = new GenericBox<>(elementToCompare);

        int result = GenericBox.compareElements(list, boxToCompare);
        System.out.println(result);


    }
}

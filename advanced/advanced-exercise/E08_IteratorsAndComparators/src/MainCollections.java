import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainCollections {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        List<String> initialCommand = Arrays.stream(scan.nextLine().split("\\s+")).collect(Collectors.toList());
        initialCommand.remove(0);
        ListIterator iterator = new ListIterator(initialCommand);


        String input = scan.nextLine();
        while (!input.equals("END")) {

            switch (input) {

                case "PrintAll":
                    iterator.printAll();
                    break;
                case "Move":
                    System.out.println(iterator.move());
                    break;
                case "HasNext":
                    System.out.println(iterator.hasNext());
                    break;
                case "Print":
                    iterator.print();
                    break;
            }


            input = scan.nextLine();
        }
    }
}

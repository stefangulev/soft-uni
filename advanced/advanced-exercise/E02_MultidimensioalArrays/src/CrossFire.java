import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CrossFire {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] dimensions = scan.nextLine().split("\\s+");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        ArrayList<ArrayList<Integer>> filed = new ArrayList<>();

        int counter = 1;

        for (int row = 0; row < rows; row++) {
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int col = 0; col < cols; col++) {
                numbers.add(counter++);
            }
            filed.add(numbers);
        }



        String input = scan.nextLine();
        while (!input.equals("Nuke it from orbit")) {
            String[] commands = input.split("\\s+");
            int rowInMatrix = Integer.parseInt(commands[0]);
            int colInMatrix = Integer.parseInt(commands[1]);
            int radius = Integer.parseInt(commands[2]);



            for (int i = colInMatrix - radius; i <= colInMatrix + radius ; i++) {
                if (rowInMatrix >= 0 && rowInMatrix < filed.size() && i >= 0 && i < filed.get(rowInMatrix).size()) {
                    filed.get(rowInMatrix).set(i, - 1);
                }
            }


            for (int i = rowInMatrix - radius; i <= rowInMatrix + radius ; i++) {
                if (i >= 0 && i < filed.size() && colInMatrix >= 0 && colInMatrix < filed.get(i).size()) {
                    filed.get(i).set(colInMatrix, -1);
                }
            }


            for (ArrayList<Integer> integers : filed) {
                removeNegativeValues(integers);

            }
            filed.removeIf(ArrayList::isEmpty);

           // filed = filed.stream().filter(l -> !l.isEmpty()).collect(Collectors.toCollection(ArrayList::new));



            input = scan.nextLine();
            }
        printMatrix(filed);


        }

    private static void removeNegativeValues(ArrayList<Integer> currentRow) {
        currentRow.removeIf(e -> e == -1);
    }

    private static void printMatrix (ArrayList<ArrayList<Integer>> matrix) {
            for (ArrayList<Integer> integers : matrix) {
                for (Integer integer : integers) {
                    System.out.print(integer + " ");
                }
                System.out.println();
            }
        }


    }


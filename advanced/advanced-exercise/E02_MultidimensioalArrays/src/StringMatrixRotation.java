import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StringMatrixRotation {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String command = scan.nextLine();

        char[][] matrix = readMatrix(scan);

    }

    private static char[][] readMatrix(Scanner scan) {
        List<String> list = new ArrayList<>();

        String input = scan.nextLine();

        while (!"END".equals(input)) {
            list.add(input);
            input = scan.nextLine();
        }




        int length = Integer.MIN_VALUE;;

        for (int i = 0; i <list.size() ; i++) {
            int current = list.get(i).length();
            if (current > length) {
                length = current;
            }
        }

        char[][] matrix = new char[list.size()][length];
        for (int i = 0; i < list.size() ; i++) {
            matrix[i] = list.get(i).toCharArray();
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col <matrix[0].length ; col++) {
                char current = matrix[row][col];

            }
        }
        return matrix;
    }

}

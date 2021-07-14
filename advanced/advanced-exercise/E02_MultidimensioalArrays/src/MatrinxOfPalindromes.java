import java.util.Scanner;

public class MatrinxOfPalindromes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");

        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        String[][] matrix = fillMatrix(rows,cols);

       printMatrix(matrix);
    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length ; row++) {
            for (int col = 0; col <matrix[0].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static String[][] fillMatrix(int rows, int cols) {
        String[][] matrix = new String[rows][cols];

        for (int row = 0; row < rows ; row++) {
            for (int col = 0; col <cols ; col++) {
                char firstLastLetter = (char) ((char) row + 97);
                char middleLetter = (char) ((char) row + col + 97);
                matrix[row][col] = (firstLastLetter + "") + (middleLetter +"") + (firstLastLetter + "");
            }
        }
        return matrix;
    }
}

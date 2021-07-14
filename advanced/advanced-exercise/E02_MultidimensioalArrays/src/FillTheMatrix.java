import java.util.Scanner;

public class FillTheMatrix {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split(", ");
        int size = Integer.parseInt(input[0]);
        String pattern = input[1];

        if (pattern.equals("A")) {
            int[][] matrix = readMatrixPatternA(size);
            printMatrix(matrix);
        } else if (pattern.equals("B")) {
            int[][] matrix = readMatrixPatternB(size);
            printMatrix(matrix);
        }


    }

    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length ; row++) {
            for (int col = 0; col <matrix[0].length ; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] readMatrixPatternA(int size) {
        int[][] matrix = new int[size][size];

        for (int row = 0; row < size; row++) {
            matrix[row][0] = row + 1;
        }

        for (int row = 0; row < size; row++) {
            for (int col = 1; col < size; col++) {
                matrix[row][col] = matrix[row][col - 1] + size;
            }
        }
        return matrix;
    }

    private static int[][] readMatrixPatternB(int size) {
        int[][] matrix = new int[size][size];

        int count = 1;

        for (int col = 0; col < size; col++) {
            if (col % 2 == 0) {
                for (int row = 0; row < size; row++) {
                    matrix[row][col] = count;
                    count++;
                }
            } else {
                for (int row = size - 1; row >= 0; row--) {
                    matrix[row][col] = count;
                    count++;
                }
            }

        }
        return matrix;
    }
}

import javax.swing.*;
import java.util.Scanner;

public class MaximalSum {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        int[][] matrix = readMatrix(scan, rows, cols);

       int[][] biggestMatrix = findTheBiggest3X3SquareMatrix(matrix);
        printMatrix(biggestMatrix);





    }

    private static void printMatrix(int[][] biggestMatrix) {
        for (int row = 0; row <biggestMatrix.length ; row++) {
            for (int col = 0; col <biggestMatrix[0].length ; col++) {
                System.out.print(biggestMatrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] findTheBiggest3X3SquareMatrix(int[][] matrix) {
        int[][] biggestMatrix = new int[3][3];

        int biggestSum = Integer.MIN_VALUE;


            for (int row = 0; row <= matrix.length - 3; row++) {
                for (int col = 0; col <= matrix[0].length - 3; col++) {
                    int currentSum = matrix[row][col] + matrix[row][col + 1] + matrix[row][col + 2] + matrix[row + 1][col] + matrix[row + 1][col + 1] +
                            matrix[row + 1][col + 2] + matrix[row + 2][col] + matrix[row + 2][col + 1] + matrix[row + 2][col + 2];
                    if (currentSum > biggestSum) {
                        biggestSum = currentSum;
                        biggestMatrix[0][0] = matrix[row][col];
                        biggestMatrix[0][1] = matrix[row][col + 1];
                        biggestMatrix[0][2] = matrix[row][col + 2];
                        biggestMatrix[1][0] = matrix[row + 1][col];
                        biggestMatrix[1][1] = matrix[row + 1][col + 1];
                        biggestMatrix[1][2] = matrix[row + 1][col + 2];
                        biggestMatrix[2][0] = matrix[row + 2][col];
                        biggestMatrix[2][1] = matrix[row + 2][col + 1];
                        biggestMatrix[2][2] = matrix[row + 2][col + 2];
                    }
                }
            }

        System.out.println(String.format("Sum = %d", biggestSum));
        return biggestMatrix;

    }

    private static int[][] readMatrix(Scanner scan, int rows, int cols) {
        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            String[] line = scan.nextLine().split("\\s+");
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = Integer.parseInt(line[col]);
            }
        }
        return matrix;
    }
}

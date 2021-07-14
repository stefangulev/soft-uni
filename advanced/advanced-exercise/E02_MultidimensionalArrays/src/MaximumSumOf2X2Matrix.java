import java.util.Scanner;

public class MaximumSumOf2X2Matrix {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] dimensions = scan.nextLine().split(", ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        int[][] matrix = readMatrix(scan, rows, cols);

        int biggestSum = Integer.MIN_VALUE;
        int[][] biggestMatrix = new int[2][2];

        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[0].length - 1; col++) {
                int currentSum = matrix[row][col] + matrix[row + 1][col] + matrix[row][col+ 1] + matrix[row + 1][col +1];

                if(currentSum > biggestSum) {
                    biggestSum = currentSum;

                            biggestMatrix[0][0] = matrix[row][col];
                            biggestMatrix[1][0] = matrix[row +1][col];
                            biggestMatrix[0][1] = matrix[row][col + 1];
                            biggestMatrix[1][1] = matrix[row + 1][col + 1];



                }


            }

        }

        printMatrix(biggestMatrix);
        System.out.println(biggestSum);

    }


    private static int[][] readMatrix (Scanner scan, int rows, int cols) {

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows ; row++) {
            String[] line = scan.nextLine().split(", ");
            for (int col = 0; col < cols ; col++) {
                matrix[row][col] = Integer.parseInt(line[col]);
            }
        }
        return matrix;
    }
    private static void printMatrix (int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col <matrix[0].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}

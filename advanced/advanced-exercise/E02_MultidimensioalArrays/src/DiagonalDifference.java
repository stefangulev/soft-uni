import java.util.Arrays;
import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int size = Integer.parseInt(scan.nextLine());

        int[][] matrix = readMatrix(scan, size);

        int firstDiagonalSum = firstDiagonalSumFinder(matrix);
        int secondDiagonalSum = secondDiagonalSumFinder(matrix);

        System.out.println(Math.abs(firstDiagonalSum - secondDiagonalSum));
    }

    private static int secondDiagonalSumFinder(int[][] matrix) {
        int sum = 0;
        int col =0;
        for (int row = matrix.length - 1; row >=0 ; row--) {
            int current = matrix[row][col];
            sum += current;
            col++;
        }
        return sum;
    }

    private static int firstDiagonalSumFinder(int[][] matrix) {
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            int current = matrix[row][row];
            sum +=current;
        }
        return sum;
    }

    private static int[][] readMatrix(Scanner scan, int size) {
        int[][] matrix = new int[size][size];

        for (int row = 0; row < size ; row++) {
            int[] arr = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;

        }
        return matrix;
    }
}

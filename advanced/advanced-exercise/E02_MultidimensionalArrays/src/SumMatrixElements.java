import java.util.Scanner;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] dimensions = scan.nextLine().split(", ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        int[][] matrix = readMatrix(scan, rows, cols);

        int sum = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col <matrix[0].length; col++) {
                int current = matrix[row][col];
                sum+=current;
            }
        }
        System.out.println(rows);
        System.out.println(cols);
        System.out.println(sum);
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
}

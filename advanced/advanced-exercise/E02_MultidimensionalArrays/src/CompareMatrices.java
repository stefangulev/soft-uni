import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] firstMatrixMetrics = scan.nextLine().split("\\s+");
        int rows = Integer.parseInt(firstMatrixMetrics[0]);
        int cols = Integer.parseInt(firstMatrixMetrics[1]);

        int[][] firstMatrix = readMatrix(scan, rows, cols);

        String[] secondMatrixMetrics = scan.nextLine().split("\\s+");
        int rows2 = Integer.parseInt(secondMatrixMetrics[0]);
        int cols2 = Integer.parseInt(secondMatrixMetrics[1]);

        int[][] secondMatrix = readMatrix(scan, rows2,cols2);

        if (rows != rows2 || cols != cols2) {
            System.out.println("not equal");
        } else {
            System.out.println(compareMatrices(firstMatrix, secondMatrix));
        }
    }


    private static int[][] readMatrix(Scanner scan, int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            int[] line = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            matrix[i] = line;
        }
        return matrix;
    }

    private static String compareMatrices(int[][] firstMatrix, int[][] secondMatrix ) {
        for (int rows = 0; rows < firstMatrix.length; rows++) {
            for (int cols = 0; cols < firstMatrix[0].length ; cols++) {
                if (firstMatrix[rows][cols] != secondMatrix[rows][cols]) {
                    return "not equal";
                }
            }
        }
        return "equal";
    }

}

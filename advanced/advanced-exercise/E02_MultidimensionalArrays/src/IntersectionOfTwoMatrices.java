import java.util.Scanner;

public class IntersectionOfTwoMatrices {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int rows = Integer.parseInt(scan.nextLine());
        int cols = Integer.parseInt(scan.nextLine());

        char[][] firstMatrix = readMatrix(scan, rows, cols);
        char[][] secondMatrix = readMatrix(scan, rows, cols);

        char[][] finalMatrix = matrixComparator(firstMatrix, secondMatrix);

        printMatrix(finalMatrix);
    }

    private static char[][] readMatrix(Scanner scan, int rows, int cols) {
        char[][] matrix = new char[rows][cols];
        for (int row = 0; row < rows; row++) {
            String[] line = scan.nextLine().split("\\s+");
            for (int col = 0; col < cols ; col++) {
                char current = line[col].charAt(0);
                matrix[row][col] = current;

            }
        }
        return matrix;
    }

    private static char[][] matrixComparator(char[][] firstMatrix, char[][] secondMatrix) {
        char[][] finalMatrix = new char[firstMatrix.length][firstMatrix[0].length];

        for (int row = 0; row < finalMatrix.length; row++) {
            for (int col = 0; col < finalMatrix[0].length; col++) {
                char firstMatrixElement = firstMatrix[row][col];
                char secondMatrixElement = secondMatrix[row][col];
                if (firstMatrixElement == secondMatrixElement) {
                    finalMatrix[row][col] = firstMatrixElement;
                } else {
                    finalMatrix[row][col] = '*';
                }
            }
        }
        return finalMatrix;
    }
    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length ; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}

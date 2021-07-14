import java.util.*;

public class Garden {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        int[][] matrix = new int[dimensions[0]][dimensions[1]];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = 0;
            }
        }

        String input = scan.nextLine();

        List<int[]> plantedCoordinates = new ArrayList<>();

        while (!input.equals("Bloom Bloom Plow")) {

            String[] tokens = input.split("\\s+");
            int currentRow = Integer.parseInt(tokens[0]);
            int currentCol = Integer.parseInt(tokens[1]);

            if (rowIsValid(currentRow, matrix) && colIsValid(currentCol, matrix)) {
                int[] positions = {currentRow, currentCol};
                plantedCoordinates.add(positions);
            } else {
                System.out.println("Invalid coordinates.");
            }


            input = scan.nextLine();
        }

        for (int[] plantedCoordinate : plantedCoordinates) {
            int row = plantedCoordinate[0];
            int col = plantedCoordinate[1];

            matrix[row][col] = 1;

            while (row-- > 0) {
                matrix[row][col] = matrix[row][col] + 1;
            }
            row = plantedCoordinate[0];
            while (row++ < matrix.length - 1) {
                matrix[row][col] = matrix[row][col] + 1;
            }
            row = plantedCoordinate[0];
            while (col-- > 0) {
                matrix[row][col] =  matrix[row][col] + 1;
            }
            col = plantedCoordinate[1];

            while (col++ < matrix[0].length - 1) {
                matrix[row][col] =  matrix[row][col] + 1;
            }
            col = plantedCoordinate[1];
        }

        printMatrix(matrix);



    }


    public static boolean rowIsValid (int row, int[][] matrix) {
        boolean rowIsValid = false;
        if (row >= 0 && row < matrix.length) {
            rowIsValid = true;
        }
        return rowIsValid;
    }

    public static boolean colIsValid (int col, int[][] matrix) {
        boolean colIsValid = false;
        if (col >= 0 && col < matrix[0].length) {
            colIsValid = true;
        }
        return colIsValid;
    }

    public static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }
}

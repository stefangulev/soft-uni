package jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            int[][] matrix = matrixGenerator(scanner);
            matrixFiller(matrix);

            String command = scanner.nextLine();
            long sum = 0;
            while (!command.equals("Let the Force be with you")) {
                evilMovement(scanner, matrix);
                sum += playerMovement(command, matrix);

                command = scanner.nextLine();
            }

        System.out.println(sum);


    }
    public static int[][] matrixGenerator (Scanner scan) {
        int[] dimensions = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[][] matrix = new int[dimensions[0]][dimensions[1]];

        return matrix;
    }

    public static void matrixFiller(int[][] matrix) {

        int value = 0;
        for (int row = 0; row <matrix.length; row++) {
            for (int col = 0; col <matrix[0].length ; col++) {
                matrix[row][col] = value++;
            }
        }
    }

    public static void evilMovement(Scanner scanner, int[][] matrix) {
        int[] evilCoordinates = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int evilRow = evilCoordinates[0];
        int evilCol = evilCoordinates[1];

        while (evilRow >= 0 && evilCol >= 0)
        {
            if (evilRow >= 0 && evilRow < matrix.length && evilCol >= 0 && evilCol < matrix[0].length)
            {
                matrix[evilRow] [evilCol] = 0;
            }
            evilRow--;
            evilCol--;
        }
    }

    public static int playerMovement(String command, int[][] matrix) {
        int[] playerCoordinates = Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
        int sum = 0;
        int playerRow = playerCoordinates[0];
        int playerCol = playerCoordinates[1];

        while (playerRow >= 0 && playerCol < matrix[1].length)
        {
            if (playerRow >= 0 && playerRow < matrix.length && playerCol >= 0 && playerCol < matrix[0].length)
            {
                sum += matrix[playerRow][playerCol];
            }

            playerCol++;
            playerRow--;
        }
        return sum;
    }
}

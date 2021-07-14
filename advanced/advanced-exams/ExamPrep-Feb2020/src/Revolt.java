import java.util.Scanner;

public class Revolt {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int size = Integer.parseInt(scan.nextLine());

        String[][] matrix = new String[size][size];

        int commandCnt = Integer.parseInt(scan.nextLine());

        boolean hasWon = false;

        for (int row = 0; row < matrix.length; row++) {
            String[] input = scan.nextLine().split("");
            matrix[row] = input;
        }

        int playerRow = 0;
        int playerCol = 0;

        for (int row = 0; row < matrix.length ; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col].equals("f")) {
                    playerRow = row;
                    playerCol = col;
                }
            }
        }
        matrix[playerRow][playerCol] = "-";


        while (commandCnt-- > 0 && !hasWon) {
            String command = scan.nextLine();

            switch (command) {
                case "up":
                    playerRow--;
                    if (playerRow < 0) {
                        playerRow = matrix.length - 1;
                    }
                    if (matrix[playerRow][playerCol].equals("B")) {
                        playerRow--;
                        if (playerRow < 0) {
                            playerRow = matrix.length - 1;
                        }
                    } else if (matrix[playerRow][playerCol].equals("T")) {
                        playerRow++;
                        if (playerRow == matrix.length) {
                            playerRow = 0;
                        }
                    } else if (matrix[playerRow][playerCol].equals("F")) {
                        matrix[playerRow][playerCol] = "f";
                        hasWon = true;
                    }
                    break;
                case "down":
                    playerRow++;
                    if (playerRow == matrix.length) {
                        playerRow = 0;
                    }
                    if (matrix[playerRow][playerCol].equals("B")) {
                        playerRow++;
                        if (playerRow == matrix.length) {
                            playerRow = 0;
                        }
                    } else if (matrix[playerRow][playerCol].equals("T")) {
                        playerRow--;
                        if (playerRow < 0 ) {
                            playerRow = matrix.length - 1;
                        }
                    } else if (matrix[playerRow][playerCol].equals("F")) {
                        matrix[playerRow][playerCol] = "f";
                        hasWon = true;
                    }
                    break;
                case "left":
                    playerCol--;
                    if (playerCol < 0) {
                        playerCol = matrix[0].length - 1;
                    }
                    if (matrix[playerRow][playerCol].equals("B")) {
                        playerCol--;
                        if (playerCol < 0) {
                            playerCol = matrix[0].length - 1;
                        }
                    } else if (matrix[playerRow][playerCol].equals("T")) {
                        playerCol++;
                        if (playerCol == matrix[0].length) {
                            playerCol = 0;
                        }
                    } else if (matrix[playerRow][playerCol].equals("F")) {
                        matrix[playerRow][playerCol] = "f";
                        hasWon = true;
                    }
                    break;
                case "right":
                    playerCol++;
                    if (playerCol == matrix[0].length) {
                        playerCol = 0;
                    }
                    if (matrix[playerRow][playerCol].equals("B")) {
                        playerCol++;
                        if (playerCol == matrix[0].length) {
                            playerCol = 0;
                        }
                    } else if (matrix[playerRow][playerCol].equals("T")) {
                        playerCol--;
                        if (playerCol < 0) {
                            playerCol = matrix[0].length - 1;
                        }
                    } else if (matrix[playerRow][playerCol].equals("F")) {
                        matrix[playerRow][playerCol] = "f";
                        hasWon = true;
                    }
                    break;
            }
        }
        matrix[playerRow][playerCol] = "f";

        if (hasWon) {
            System.out.println("Player won!");
        } else {
            System.out.println("Player lost!");
        }
        for (int row = 0; row < matrix.length ; row++) {
            for (int col = 0; col <matrix[0].length ; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }


    }
}

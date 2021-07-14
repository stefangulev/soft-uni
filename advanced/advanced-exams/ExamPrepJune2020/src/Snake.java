import java.util.Scanner;

public class Snake {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int size = Integer.parseInt(scan.nextLine());

        String[][] matrix = new String[size][size];

        int snakeRow = 0;
        int snakeCol = 0;


        for (int row = 0; row < matrix.length; row++) {
            String[] line = scan.nextLine().split("");
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = line[col];
                if (matrix[row][col].equals("S")) {
                    snakeRow = row;
                    snakeCol = col;
                }
            }
        }

        matrix[snakeRow][snakeCol] = ".";

        int foodCnt = 0;
        boolean gameIsOver = false;
        String input = scan.nextLine();
        while (foodCnt < 10) {

            if (input.equals("up")) {
                snakeRow--;
                if (snakeRow < 0) {
                    gameIsOver = true;
                    break;
                }
            } else if (input.equals("down")) {
                snakeRow++;
                if (snakeRow == matrix.length) {
                    gameIsOver = true;
                    break;
                }
            } else if (input.equals("left")) {
                snakeCol--;
                if (snakeCol < 0) {
                    gameIsOver = true;
                    break;
                }
            } else if (input.equals("right")) {
                snakeCol++;
                if (snakeCol == matrix[0].length) {
                    gameIsOver = true;
                    break;
                }
            }

            if (matrix[snakeRow][snakeCol].equals("*")) {
                matrix[snakeRow][snakeCol] = ".";
                foodCnt++;
                if (foodCnt == 10) {
                    break;
                }
            } else if (matrix[snakeRow][snakeCol].equals("-")) {
                matrix[snakeRow][snakeCol] = ".";
            } else if (matrix[snakeRow][snakeCol].equals("B")) {
                matrix[snakeRow][snakeCol] = ".";
                int burrowRow = 0;
                int burrowCol = 0;
                for (int row = 0; row < matrix.length; row++) {
                    for (int col = 0; col < matrix[0].length; col++) {
                        if (matrix[row][col].equals("B")) {
                            burrowRow = row;
                            burrowCol = col;
                            break;
                        }
                    }
                }
                snakeRow = burrowRow;
                snakeCol = burrowCol;
                matrix[burrowRow][burrowCol] = ".";
            }

                input = scan.nextLine();

        }

        if (!gameIsOver) {
            matrix[snakeRow][snakeCol] = "S";
        }

        if (gameIsOver) {
            System.out.println("Game over!");
        } else {
            System.out.println("You won! You fed the snake.");
        }

        System.out.println(String.format("Food eaten: %d", foodCnt));

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col <matrix[0].length ; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}

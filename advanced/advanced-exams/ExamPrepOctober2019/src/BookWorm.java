import java.util.Scanner;

public class BookWorm {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        StringBuilder initialText = new StringBuilder(scan.nextLine());
        int size = Integer.parseInt(scan.nextLine());

        String[][] matrix = new String[size][size];

        int playerRow = 0;
        int playerCol = 0;

        for (int rows = 0; rows < matrix.length; rows++) {
            String[] line = scan.nextLine().split("");
            for (int cols = 0; cols < matrix[0].length; cols++) {
                matrix[rows][cols] = line[cols];
                if (matrix[rows][cols].equals("P")) {
                    playerRow = rows;
                    playerCol = cols;
                }
            }
        }

        matrix[playerRow][playerCol] = "-";

        String input = scan.nextLine();

        while (!input.equals("end")) {

            switch (input) {
                case "up":
                    playerRow--;
                    if (playerRow < 0) {
                        playerRow++;
                        removeLastChar(initialText);
                    }
                    break;
                case "down":
                    playerRow++;
                    if (playerRow == matrix.length) {
                        playerRow--;
                        removeLastChar(initialText);
                    }
                    break;
                case "left":
                    playerCol--;
                    if (playerCol < 0) {
                        playerCol++;
                        removeLastChar(initialText);
                    }
                    break;
                case "right":
                    playerCol++;
                    if (playerCol == matrix[0].length) {
                        playerCol--;
                        removeLastChar(initialText);
                    }
                    break;
            }

            if (!matrix[playerRow][playerCol].equals("-")) {
                initialText.append(matrix[playerRow][playerCol]);
                matrix[playerRow][playerCol] = "-";
            }

            input = scan.nextLine();
        }

        matrix[playerRow][playerCol] = "P";

        System.out.println(String.valueOf(initialText));
        printMatrix(matrix);


    }
    public static void removeLastChar(StringBuilder initialText) {
        if (initialText.length() > 0) {
            initialText.deleteCharAt(initialText.length() - 1);
        }
    }

    public static void printMatrix (String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}

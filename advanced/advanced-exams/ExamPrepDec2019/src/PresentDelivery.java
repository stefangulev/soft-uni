import java.util.Arrays;
import java.util.Scanner;

public class PresentDelivery {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int presentCnt = Integer.parseInt(scan.nextLine());
        int matrixSize = Integer.parseInt(scan.nextLine());

        String[][] matrix = new String[matrixSize][matrixSize];

        int santaRow = 0;
        int santaCol = 0;


        int niceKidsCounts = 0;

        for (int rows = 0; rows < matrix.length; rows++) {
            String[] row = scan.nextLine().split("\\s+");
            for (int cols = 0; cols < matrix[0].length; cols++) {
                matrix[rows][cols] = row[cols];
                if (matrix[rows][cols].equals("S")) {
                    santaRow = rows;
                    santaCol = cols;
                } else if (matrix[rows][cols].equals("V")) {
                    niceKidsCounts++;
                }
            }
        }
        matrix[santaRow][santaCol] = "-";

        String input = scan.nextLine();
        while (!input.equals("Christmas morning")) {

            switch (input) {
                case "up":
                    santaRow--;
                    break;
                case "down":
                    santaRow++;
                    break;
                case "left":
                    santaCol--;
                    break;
                case "right":
                    santaCol++;
                    break;
            }

            if (matrix[santaRow][santaCol].equals("V")) {
                presentCnt--;
                matrix[santaRow][santaCol] = "-";
                if (presentCnt <= 0) {
                    System.out.println("Santa ran out of presents!");
                    break;
                }
            } else if (matrix[santaRow][santaCol].equals("X")) {
                matrix[santaRow][santaCol] = "-";
            } else if (matrix[santaRow][santaCol].equals("C")) {
                if (matrix[santaRow - 1][santaCol].equals("V") ||matrix[santaRow - 1][santaCol].equals("X")) {
                    presentCnt--;
                    matrix[santaRow - 1][santaCol] = "-";
                    if (presentCnt <= 0) {
                        System.out.println("Santa ran out of presents!");
                        break;
                    }

                }
                if  (matrix[santaRow + 1][santaCol].equals("V") ||matrix[santaRow + 1][santaCol].equals("X")) {
                    presentCnt--;
                    matrix[santaRow + 1][santaCol] = "-";
                    if (presentCnt <= 0) {
                        System.out.println("Santa ran out of presents!");
                        break;
                    }

                }
                if (matrix[santaRow][santaCol - 1].equals("V") ||matrix[santaRow][santaCol - 1].equals("X")) {
                    presentCnt--;
                    matrix[santaRow][santaCol - 1] = "-";
                    if (presentCnt <= 0) {
                        System.out.println("Santa ran out of presents!");
                        break;
                    }

                }
                if (matrix[santaRow][santaCol + 1].equals("V") ||matrix[santaRow][santaCol + 1].equals("X")) {
                    presentCnt--;
                    matrix[santaRow][santaCol + 1] = "-";
                    if (presentCnt <= 0) {
                        System.out.println("Santa ran out of presents!");
                        break;
                    }

                }
            }

            input = scan.nextLine();
        }

        matrix[santaRow][santaCol] = "S";

        int niceKidsLeft = 0;

        for (int rows = 0; rows < matrix.length; rows++) {
            for (int cols = 0; cols < matrix[0].length; cols++) {
                System.out.print(matrix[rows][cols] +" ");
                if (matrix[rows][cols].equals("V")) {
                    niceKidsLeft++;
                }
            }
            System.out.println();
        }
        if (niceKidsLeft == 0) {
            System.out.println(String.format("Good job, Santa! %d happy nice kid/s.", niceKidsCounts));
        } else {
            System.out.println(String.format("No presents for %d nice kid/s.", niceKidsLeft));
        }


    }


}

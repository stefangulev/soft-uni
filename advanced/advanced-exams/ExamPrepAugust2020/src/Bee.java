import java.util.Scanner;

public class Bee {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int size = Integer.parseInt(scan.nextLine());

        String[][] matrix = new String[size][size];

        int beeRow = 0;
        int beeCol = 0;

        for (int row = 0; row < matrix.length; row++) {
            String[] line = scan.nextLine().split("");
            for (int col = 0; col < matrix[0].length; col++) {
                matrix[row][col] = line[col];

                if (matrix[row][col].equals("B")) {
                    beeRow = row;
                    beeCol = col;
                }
            }
        }

        matrix[beeRow][beeCol] = ".";

        String input = scan.nextLine();

        int pollinatedFlowers = 0;

      loop:  while (!input.equals("End")) {

            switch (input) {
                case "up":
                    beeRow--;
                    if (beeRow < 0) {
                        System.out.println("The bee got lost!");
                        break loop;
                    }
                    break;
                case "down":
                    beeRow++;
                    if (beeRow == matrix.length) {
                        System.out.println("The bee got lost!");
                        break loop;
                    }
                    break;
                    case "left":
                        beeCol--;
                        if (beeCol < 0) {
                            System.out.println("The bee got lost!");
                            break loop;
                        }
                    break;
                case "right":
                    beeCol++;
                    if (beeCol == matrix[0].length) {
                        System.out.println("The bee got lost!");
                        break loop;
                    }
                    break;
            }

            String currentPosition = matrix[beeRow][beeCol];

            if (currentPosition.equals("f")) {
                pollinatedFlowers++;
                matrix[beeRow][beeCol] = ".";
            } else if (currentPosition.equals("O")) {
                matrix[beeRow][beeCol]= ".";
                switch (input) {
                    case "up":
                        beeRow--;
                        if (beeRow < 0) {
                            System.out.println("The bee got lost!");
                            break loop;
                        }
                        break;
                    case "down":
                        beeRow++;
                        if (beeRow == matrix.length) {
                            System.out.println("The bee got lost!");
                            break loop;
                        }
                        break;
                    case "left":
                        beeCol--;
                        if (beeCol < 0) {
                            System.out.println("The bee got lost!");
                            break loop;
                        }
                        break;
                    case "right":
                        beeCol++;
                        if (beeCol == matrix[0].length) {
                            System.out.println("The bee got lost!");
                            break loop;
                        }
                        break;
                }

                if (matrix[beeRow][beeCol].equals("f")) {
                    pollinatedFlowers++;
                    matrix[beeRow][beeCol] = ".";
                }
            }


            input = scan.nextLine();
        }

        if (beeRow >= 0 && beeRow < matrix.length && beeCol >=0 && beeCol < matrix[0].length) {
            matrix[beeRow][beeCol] = "B";
        }

        if (pollinatedFlowers < 5) {
            System.out.println(String.format("The bee couldn't pollinate the flowers, she needed %d flowers more", 5 - pollinatedFlowers));
        } else {
            System.out.println(String.format("Great job, the bee manage to pollinate %d flowers!", pollinatedFlowers));
        }

        printMatrix(matrix);




    }

    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

}

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] dimensions = scan.nextLine().split("\\s+");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        String[][] matrix = readMatrix(scan, rows, cols);

        String regex = "swap [0-9]{1,2} [0-9]{1,2} [0-9]{1,2} [0-9]{1,2}$";
        Pattern pattern = Pattern.compile(regex);

        String input = scan.nextLine();

        while (!"END".equals(input)) {
            Matcher matcher = pattern.matcher(input);

            if (matcher.find()) {
                String[] command = input.split("\\s+");
                int firstRow = Integer.parseInt(command[1]);
                int firstCol = Integer.parseInt(command[2]);
                int secondRow = Integer.parseInt(command[3]);
                int secondCol = Integer.parseInt(command[4]);

                if (firstRow > -1 && firstCol > -1 && secondRow > -1 && secondCol > -1 && firstRow < matrix.length && secondRow < matrix.length
                        && firstCol < matrix[0].length && secondCol < matrix[0].length) {

                    matrix = swapMatrixElement(matrix, firstRow, firstCol, secondRow, secondCol);
                    printMatrix(matrix);
                } else {
                    System.out.println("Invalid input!");
                }

            } else {
                    System.out.println("Invalid input!");
                }

                input = scan.nextLine();
            }


        }


    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row <matrix.length ; row++) {
            for (int col = 0; col <matrix[0].length ; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

        private static String[][] swapMatrixElement (String[][]matrix,int firstRow, int firstCol, int secondRow,
        int secondCol){

            String first = matrix[firstRow][firstCol];
            String second = matrix[secondRow][secondCol];

            matrix[firstRow][firstCol] = second;
            matrix[secondRow][secondCol] = first;


            return matrix;
        }

        private static String[][] readMatrix (Scanner scan,int rows, int cols){
            String[][] matrix = new String[rows][cols];

            for (int row = 0; row < matrix.length; row++) {
                String[] arr = scan.nextLine().split("\\s+");
                for (int col = 0; col < matrix[0].length; col++) {
                    matrix[row][col] = arr[col];
                }
            }
            return matrix;
        }
    }


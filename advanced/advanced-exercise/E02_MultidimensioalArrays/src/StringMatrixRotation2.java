import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringMatrixRotation2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String rotation = scan.nextLine();
        int angle = Integer.parseInt(rotation.split("[()]")[1]) % 360;

        String input = scan.nextLine();

        List<String> words = new ArrayList<>();

        int maxLength = Integer.MIN_VALUE;

        while (!input.equals("END")) {
            words.add(input);
            if (input.length() > maxLength) {
                maxLength = input.length();
            }

            input = scan.nextLine();
        }


        int rows = words.size();
        int cols = maxLength;

        char[][] matrix = new char[rows][cols];

        for (int row = 0; row < rows ; row++) {
            for (int col = 0; col < cols ; col++) {
                if (col < words.get(row).length()) {
                    matrix[row][col] = words.get(row).charAt(col);
                } else {
                    matrix[row][col] = ' ';
                }

            }
        }
        if (angle == 90) {
            for (int col = 0; col < cols ; col++) {
                for (int row = rows - 1; row >= 0 ; row--) {
                    System.out.print(matrix[row][col]);
                }
                System.out.println();
            }

            } else if (angle == 180) {
            for (int row = rows - 1; row >= 0 ; row--) {
                for (int col = cols -1 ; col >= 0 ; col--) {
                    System.out.print(matrix[row][col]);
                }
                System.out.println();
            }
        } else if (angle == 270) {
            for (int col = cols - 1; col >=0 ; col--) {
                for (int row = 0; row < rows ; row++) {
                    System.out.print(matrix[row][col]);
                }
                System.out.println();
            }
        } else if (angle == 0) {
            for (int row = 0; row < rows ; row++) {
                for (int col = 0; col < cols ; col++) {
                    System.out.print(matrix[row][col]);
                }
                System.out.println();
            }
        }

        }
        }




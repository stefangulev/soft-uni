import java.util.Arrays;
import java.util.Scanner;

public class PrintDiagonalsOfSquareMatrix {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n  = Integer.parseInt(scan.nextLine());

        int[][] matrix = readMatirx(scan,n);


        for (int i = 0; i < n ; i++) {
            int current = matrix[i][i];
            System.out.print(current + " ");
        }

        System.out.println();
        int col = 0;
        for (int i = n - 1; i >= 0 ; i--) {
            int current = matrix[i][col];
            System.out.print(current + " ");
            col++;
        }
    }

    private static int[][] readMatirx(Scanner scan, int n) {
        
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n ; i++) {
            int[] line = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray(); 
            matrix[i] = line;
        }
        return matrix;
    }
}

import java.util.*;

public class ReverseMatrixDiagonals {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] input = scan.nextLine().split("\\s+");
        int rows = Integer.parseInt(input[0]);
        int cols = Integer.parseInt(input[1]);

        ArrayDeque<List<Integer>> stack = new ArrayDeque<>();

        int[][] matrix = readMatrix(scan, rows, cols);
        List<Integer> start = new ArrayList<>();

        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                List<Integer> numbers = new ArrayList<>();
                if (matrix[row][col] != -2) {
                    numbers.add(matrix[row][col]);
                    matrix[row][col] = -2;
                    int currentColumn = col;
                    for (int i = row - 1; i >= 0; i--) {
                        currentColumn++;
                        if (currentColumn < matrix[0].length) {
                            numbers.add(matrix[i][currentColumn]);
                            matrix[i][currentColumn] = -2;
                        }
                    }
                }
                if (!numbers.isEmpty()) {
                    stack.push(numbers);
                }
            }


        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toString().replaceAll("[\\[\\],]", ""));
        }
    }


    private static int[][] readMatrix(Scanner scan, int rows, int cols) {


        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows ; row++) {
            int[] arr = Arrays.stream(scan.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = arr;
        }
        return matrix;
    }
}

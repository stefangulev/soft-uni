import java.util.*;

public class PositionsOf {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[][] matrix = readMatrix(scan);

        int numberToFind = Integer.parseInt(scan.nextLine());

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col <matrix[0].length ; col++) {
                int current = matrix[row][col];
                if (current == numberToFind) {
                    queue.offer(row);
                    queue.offer(col);
                }
            }
        }

        if (queue.isEmpty()) {
            System.out.println("not found");
            return;
        }

        while (!queue.isEmpty()) {
            System.out.println(queue.poll() + " " + queue.poll());
        }



    }


    private static int[][] readMatrix(Scanner scan) {

        String[] dimensions = scan.nextLine().split("\\s+");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows ; row++) {
            String[] line = scan.nextLine().split("\\s+");
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = Integer.parseInt(line[col]);
            }
        }
        return matrix;
    }
}

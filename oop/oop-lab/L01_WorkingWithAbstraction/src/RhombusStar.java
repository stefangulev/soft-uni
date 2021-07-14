import java.util.Scanner;

public class RhombusStar {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());

        printTopHalf(n);
        printBottomHalf(n);


    }

    private static void printBottomHalf(int n) {
        for (int i = n - 1; i > 0 ; i--) {
            printLine(i , n);
        }


    }

    public static void printTopHalf(int n) {
        for (int i = 1; i <= n ; i++) {
           printLine(i , n);
        }
    }


    public static void printLine(int i, int n ) {
        printPaddingSpace(i ,n);
        printStars(i);
        System.out.println();
    }
    public static void printPaddingSpace(int i , int n) {
        for (int j = i; j < n ; j++) {
            System.out.print(" ");
        }
    }

    public static void printStars(int i) {
        for (int k = 0; k < i; k++) {
            System.out.print("* ");
        }
    }
}

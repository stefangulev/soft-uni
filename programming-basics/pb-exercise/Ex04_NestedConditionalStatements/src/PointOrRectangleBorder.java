import java.util.Scanner;

public class PointOrRectangleBorder {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double x1 = Double.parseDouble(scan.nextLine());
        double y1 = Double.parseDouble(scan.nextLine());
        double x2 = Double.parseDouble(scan.nextLine());
        double y2 = Double.parseDouble(scan.nextLine());
        double x = Double.parseDouble(scan.nextLine());
        double y = Double.parseDouble(scan.nextLine());
        boolean firstCondition = (x == x1 || x == x2) && (y >= y1 && y <= y2);
        boolean secondCondition = (y == y1 || y == y2) && (x >= x1 && x <= x2);

        if (firstCondition || secondCondition) {
            System.out.println("Border");
        } else {
            System.out.println("Inside / Outside");
        }

    }
}

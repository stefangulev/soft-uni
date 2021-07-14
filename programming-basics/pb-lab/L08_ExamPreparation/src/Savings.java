import java.util.Scanner;

public class Savings {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double income = Double.parseDouble(scan.nextLine());
        int monthsAvailable = Integer.parseInt(scan.nextLine());
        double personalExpenses = Double.parseDouble(scan.nextLine());

        double unexpectedExpenses = income * 0.3;
        double moneyAvailable = income - personalExpenses - unexpectedExpenses;
        double totalSaved = moneyAvailable * monthsAvailable;
        double percentageToSave = moneyAvailable / income * 100;

        System.out.printf("She can save %.2f%%%n", percentageToSave);
        System.out.printf("%.2f", totalSaved);


    }
}

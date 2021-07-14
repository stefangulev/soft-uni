import java.util.Scanner;
import java.math.BigInteger;

public class SumBigInteger {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        BigInteger firstNumber = new BigInteger(scan.nextLine());
       BigInteger secondNumber = new BigInteger(scan.nextLine());

       BigInteger totalSum = firstNumber.add(secondNumber);

        System.out.println(totalSum);


    }
}

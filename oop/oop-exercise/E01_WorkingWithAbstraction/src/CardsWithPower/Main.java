package CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String rank = scan.nextLine();
        String suit = scan.nextLine();

        Card card = new Card(RankPowers.valueOf(rank).name(), SuitPowers.valueOf(suit).name(),
                RankPowers.valueOf(rank).getPower() + SuitPowers.valueOf(suit).getSuit());

        System.out.println(card);
    }
}

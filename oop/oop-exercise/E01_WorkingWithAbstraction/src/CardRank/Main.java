package CardRank;

public class Main {
    public static void main(String[] args) {
        CardRank[] cards = CardRank.getValues();

        System.out.println("Card Ranks:");
        for (CardRank card : cards) {
            System.out.println(card);
        }
    }
}

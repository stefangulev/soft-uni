package CardSuit;

public class Main {
    public static void main(String[] args) {
        CardSuit[] cards = CardSuit.getCardValues();

        System.out.println("Card Suits:");
        for (CardSuit card : cards) {
            System.out.println(String.format("Ordinal value: %d; Name value: %s", card.ordinal(), card.name()));
        }
    }
}

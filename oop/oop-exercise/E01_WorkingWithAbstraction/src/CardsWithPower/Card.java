package CardsWithPower;

public class Card {
    private String rank;
    private String suit;
    private int power;

    public Card(String rank, String suit, int power) {
        this.rank = rank;
        this.suit = suit;
        this.power = power;
    }

    @Override
    public String toString() {
        return String.format("Card name: %s of %s; Card power: %d", this.rank, this.suit, this.power);
    }
}

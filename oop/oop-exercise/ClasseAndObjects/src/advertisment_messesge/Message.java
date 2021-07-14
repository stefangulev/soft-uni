package advertisment_messesge;

public class Message {
    String phrases;
    String events;
    String author;
    String town;


    Message(String phrases, String events, String author, String town) {
        this.phrases = phrases;
        this.events = events;
        this.author = author;
        this.town = town;

    }

    @Override
    public String toString() {
        return String.format("%s %s %s - %s", this.phrases, this.events, this.author, this.town);
    }
}

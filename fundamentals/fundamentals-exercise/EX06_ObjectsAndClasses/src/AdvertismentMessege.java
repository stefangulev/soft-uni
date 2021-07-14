import java.util.Random;
import java.util.Scanner;

public class AdvertismentMessege {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        String[] phrases = {"Excellent product.", "Such a great product.", "I always use that product.", "Best product of its category.",
                "Exceptional product.", "I can’t live without this product."};
        String[] events = {"Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!",
                "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!"};
        String [] authors = {"Diana" , "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};
        String [] cities = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};

        Random rand = new Random();

        int messagesCount = Integer.parseInt(scan.nextLine());

        for (int i = 0; i < messagesCount ; i++) {
            String randomPhrase = phrases[rand.nextInt(phrases.length)];
            String randomEvent = events[rand.nextInt(events.length)];
            String randomAuthor = authors[rand.nextInt(authors.length)];
            String randomCity = cities[rand.nextInt(cities.length)];

            System.out.printf("%s %s %s - %s%n", randomPhrase, randomEvent, randomAuthor, randomCity);
        }


    }
}

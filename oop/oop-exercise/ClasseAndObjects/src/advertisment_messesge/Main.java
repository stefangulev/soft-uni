package advertisment_messesge;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] phrasesarr = {"Excellent product.", "Such a great product.", "I always use that product.", "Best product of its category."
                , "Exceptional product.", "I can’t live without this product."};


        String [] eventsarr = {"Now I feel good.", "I have succeeded with this product.", "Makes miracles. I am happy of the results!"
                , "I cannot believe but now I feel awesome.", "Try it yourself, I am very satisfied.", "I feel great!"};

        String [] authorsarr = {"Diana", "Petya", "Stella", "Elena", "Katya",
            "Iva", "Annie", "Eva"};

        String [] townsarrr = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};


        Random rnd = new Random();

        int n = Integer.parseInt(scan.nextLine());

        for (int i = 0; i <n ; i++) {
            String phrases = phrasesarr[rnd.nextInt(phrasesarr.length)];
            String events = eventsarr[rnd.nextInt(eventsarr.length)];
            String authors = authorsarr[rnd.nextInt(authorsarr.length)];
            String towns = townsarrr[rnd.nextInt(townsarrr.length)];

            Message message = new Message (phrases, events, authors, towns);

            System.out.println(message);


        }





    }
}

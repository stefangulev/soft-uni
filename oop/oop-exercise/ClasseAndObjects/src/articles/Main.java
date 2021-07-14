package articles;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] article = scan.nextLine().split(", ");

        int n = Integer.parseInt(scan.nextLine());

        String title = article[0];
        String content = article[1];
        String author = article[2];

        Articles articles = new Articles(title, content, author);

        for (int i = 0; i < n; i++) {
            String[] command = scan.nextLine().split(": ");

            switch (command[0]) {

                case "Edit":
                    articles.edit(command[1]);
                    break;
                case "ChangeAuthor":
                    articles.changeAuthor(command[1]);
                    break;
                case "Rename":
                    articles.rename(command[1]);
                    break;

            }

        }
        System.out.println(articles);


    }
}

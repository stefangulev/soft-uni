import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCount {
    public static void main(String[] args) throws IOException {

        BufferedReader wordReader = new BufferedReader(new FileReader("words.txt"));

        String[] words = wordReader.readLine().split("\\s+");

        Map<String, Integer> wordsCounter = new HashMap<>();

        for (String word : words) {
            wordsCounter.put(word, 0);
        }
        BufferedReader textReader = new BufferedReader(new FileReader("text.txt"));

        Scanner scan = new Scanner(textReader);

        String word = scan.next();

        while (scan.hasNext()) {
            if (wordsCounter.containsKey(word)) {
                wordsCounter.put(word, wordsCounter.get(word) + 1);
            }

            word = scan.next();
        }

        PrintWriter writer = new PrintWriter("results.txt");
        wordsCounter.entrySet().stream().sorted((l, r) -> r.getValue()
                .compareTo(l.getValue())).forEach(e -> writer.write(String.format("%s - %d\n", e.getKey(), e.getValue())));

        writer.flush();







    }
}

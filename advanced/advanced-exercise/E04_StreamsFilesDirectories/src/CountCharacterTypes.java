import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountCharacterTypes {
    public static void main(String[] args) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        PrintWriter writer = new PrintWriter("charater-types.txt");

        String vowelTable = "aeiou";
        String punctuationTable = "!,.?";

        Stream<String> lines = reader.lines();

        List<String> listOfLines = lines.collect(Collectors.toList());

        int vowelsCount = 0;
        int consonantsCount = 0;
        int punctuationCount = 0;

        for (String listOfLine : listOfLines) {
            for (int i = 0; i <listOfLine.length(); i++) {
                char current = listOfLine.charAt(i);

                if (vowelTable.contains(String.valueOf(current))) {
                    vowelsCount++;
                } else if (punctuationTable.contains(String.valueOf(current))) {
                    punctuationCount++;
                } else if (current == ' ') {

                } else {
                    consonantsCount++;
                }
            }
        }
        writer.write(String.format("Vowels: %d\n", vowelsCount));
        writer.write(String.format("Consonants: %d\n", consonantsCount));
        writer.write(String.format("Punctuation: %d\n", punctuationCount));
        writer.flush();

    }

}

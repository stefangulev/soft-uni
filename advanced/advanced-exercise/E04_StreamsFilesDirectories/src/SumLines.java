import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SumLines {
    public static void main(String[] args) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        Stream<String> lines = reader.lines();

        List<String> list = lines.collect(Collectors.toList());

        for (String s : list) {
            int sum = 0;
            for (int i = 0; i < s.length() ; i++) {
                char current = s.charAt(i);
                sum += current;

            }
            System.out.println(sum);

        }

    }
}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SumBytes {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        String line = reader.readLine();

        int sum = 0;

        while (line != null) {
            for (int i = 0; i < line.length(); i++) {
                char current = line.charAt(i);
                sum += current;
            }

            line = reader.readLine();

        }

        System.out.println(sum);
    }
}

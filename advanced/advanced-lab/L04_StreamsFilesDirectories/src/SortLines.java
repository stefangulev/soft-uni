import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SortLines {
    public static void main(String[] args) throws IOException {

        Path path = Paths.get("input.txt");

        List<String> lines = Files.readAllLines(path);

        FileWriter writer = new FileWriter("sorted-string.txt");

        Collections.sort(lines);

        for (String line : lines) {
           writer.write(line);
           writer.write('\n');
        }

        writer.flush();
    }
}

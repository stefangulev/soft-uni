import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineNumbers {
    public static void main(String[] args) throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new FileReader("inputLineNumbers.txt"));

        PrintWriter writer = new PrintWriter("linesWithNumbers.txt");

        Stream<String> lines = reader.lines();

        List<String> lineList = lines.collect(Collectors.toList());

        for (int i = 0; i < lineList.size(); i++) {
            String numberedLine = String.format("%d. %s\n", i + 1, lineList.get(i));
            writer.write(numberedLine);
        }
        writer.flush();
    }
}

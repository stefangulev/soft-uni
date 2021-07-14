import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WriteEveryThirdLine {
    public static void main(String[] args) {



        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

            BufferedWriter writer = new BufferedWriter(new FileWriter("print-every-third.txt"));

            Stream<String> lines = reader.lines();

            List<String> stringLines = lines.collect(Collectors.toUnmodifiableList());

            for (int i = 2; i < stringLines.size(); i+=3) {
                writer.write(stringLines.get(i));
                writer.newLine();
            }

            writer.flush();


        } catch (FileNotFoundException e) {
            System.out.println("BOOM");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

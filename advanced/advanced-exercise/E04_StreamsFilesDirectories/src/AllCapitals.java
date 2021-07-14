import java.io.*;

public class AllCapitals {
    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));

        FileWriter writer = new FileWriter("output.txt");

        String line = reader.readLine();

        while (line != null) {

            for (int i = 0; i < line.length(); i++) {
                char current = line.charAt(i);

                writer.write(Character.toUpperCase(current));
            }
            writer.write('\n');

            line = reader.readLine();
        }
        writer.flush();

    }
}

import java.io.*;

public class NbuConvertor {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\stefangulev\\Desktop\\uchebnik_mejdunaroden_marketing"));
        FileWriter writer = new FileWriter("uchebnik.txt");

        String line = reader.readLine();

        String asd = "levski";
        String wee = asd.

        while (line != null) {
            writer.write(line);
        }
    }
}

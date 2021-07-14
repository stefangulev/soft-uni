import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class WriteToFile {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\stefangulev\\Documents\\SoftUni Rescources\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        FileInputStream in = new FileInputStream(path);

        Scanner scan = new Scanner(in);
        String table = ",.!?";

        FileOutputStream out = new FileOutputStream("output.txt");

        while (scan.hasNext()) {
            String line = scan.nextLine();
            for (int i = 0; i < line.length(); i++) {
                char current = line.charAt(i);
                if (!table.contains(String.valueOf(current))) {
                    out.write(current);
                }
            }
            out.write('\n');
        }


    }
}

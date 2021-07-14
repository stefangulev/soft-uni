import java.io.*;
import java.util.Scanner;

public class ExtractIntegers {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\stefangulev\\Documents\\SoftUni Rescources\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";
        FileInputStream in = new FileInputStream(path);

        Scanner scan = new Scanner(in);

        PrintWriter writer = new PrintWriter("extract-integers.txt");


        while (scan.hasNext()) {

            if (scan.hasNextInt()) {
                int current = scan.nextInt();
                writer.println(current);


            }
            scan.next();
        }
        writer.flush();
    }
}

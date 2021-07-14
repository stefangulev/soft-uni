import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\stefangulev\\Documents\\SoftUni Rescources\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        FileInputStream fileStream = new FileInputStream(path);

        int oneByte = fileStream.read();

        while (!(oneByte == -1)) {
            System.out.print(Integer.toBinaryString(oneByte) + " ");

             oneByte = fileStream.read();
        }
    }
}

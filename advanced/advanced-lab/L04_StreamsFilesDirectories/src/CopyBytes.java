import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class CopyBytes {
    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\stefangulev\\Documents\\SoftUni Rescources\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\input.txt";

        FileInputStream in = new FileInputStream(path);

        FileOutputStream out = new FileOutputStream("bytes.txt");

        int nextByte = in.read();

        while (nextByte != - 1) {

            if (nextByte == ' ' || nextByte == '\n') {
                out.write(nextByte);
            } else {

                out.write(String.valueOf(nextByte).getBytes());
            }

            nextByte = in.read();
        }

    }
}

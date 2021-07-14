import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ListFiles {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\stefangulev\\Documents\\SoftUni Rescources\\04. Java-Advanced-Files-and-Streams-Lab-Resources\\Files-and-Streams");

        File[] files = file.listFiles();

        for (File oneFile : files) {
            if (!oneFile.isDirectory()) {
                System.out.println(oneFile.getName() + ": " + "[" + oneFile.length() + "]");
            }
        }
    }
}

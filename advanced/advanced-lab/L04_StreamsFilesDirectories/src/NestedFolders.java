import java.io.File;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.Stream;

public class NestedFolders {
    public static void main(String[] args) {
        File file = new File("C:\\\\Users\\\\stefangulev\\\\Documents\\\\SoftUni Rescources\\\\04. Java-Advanced-Files-and-Streams-Lab-Resources" +
                "\\\\Files-and-Streams");

        Deque<File> queue = new ArrayDeque<>();

        queue.add(file);

        int count =0;

        while (!queue.isEmpty()) {
            File f = queue.poll();
            count++;
            System.out.println(f.getName());
            File[] files = f.listFiles();
            for (File innerFile : files) {
                if (innerFile.isDirectory()) {
                    queue.offer(innerFile);
                }
            }
        }
        System.out.printf("%d folders", count);


    }
}

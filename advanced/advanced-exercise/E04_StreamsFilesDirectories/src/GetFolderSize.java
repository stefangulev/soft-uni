import java.io.File;
import java.util.ArrayDeque;

public class GetFolderSize {
    public static void main(String[] args) {

        File file = new File("C:\\Users\\stefangulev\\IdeaProjects\\E04_StreamsFilesDirectories\\Exercises Resources");

        File[] files = file.listFiles();

        long sum = 0;
        for (File file1 : files) {
            long current = file1.length();

            sum+=current;

        }
        System.out.println("Folder size: " + sum);

    }
}

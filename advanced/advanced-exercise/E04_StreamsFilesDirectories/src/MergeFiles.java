import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeFiles {
    public static void main(String[] args) throws FileNotFoundException {

        BufferedReader first = new BufferedReader(new FileReader("inputOne.txt"));
        BufferedReader second = new BufferedReader(new FileReader("inputTwo.txt"));



        Stream<String> lines = first.lines();
        Stream<String> lines1 = second.lines();

        List<String> firstFileList = lines.collect(Collectors.toList());
        List<String> secondFileList = lines1.collect(Collectors.toList());
        firstFileList.addAll(secondFileList);

        PrintWriter writer = new PrintWriter("mergedFiles.txt");

        for (String s : firstFileList) {
        writer.write(s);
        writer.write('\n');
        }

        writer.flush();



    }
}

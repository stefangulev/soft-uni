import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

        String[] empty = new String[4];

        List<String> emptyList =Arrays.asList(empty);

        String[] full = new String[4];
        full[0] = "levski";
        full[1] = "levski";
        full[2] = "levski";

        List<String> notEmpty = Arrays.asList(full);

        System.out.println();
    }
}

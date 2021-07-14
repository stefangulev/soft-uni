import java.util.ArrayDeque;
import java.util.Deque;

public class Demo {
    public static void main(String[] args) {

        Deque<Integer> test = new ArrayDeque<>();

        test.push(1);
        test.push(1);
        test.push(1);

        System.out.println(test);
    }
}

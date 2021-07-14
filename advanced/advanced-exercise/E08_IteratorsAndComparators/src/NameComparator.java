import java.util.Comparator;

public class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person first, Person second) {
        if (first.getName().length() > second.getName().length()) {
            return 1;
        } else if (first.getName().length() < second.getName().length()) {
            return -1;
        } else {
            char left = Character.toLowerCase(first.getName().charAt(0));
            char right = Character.toLowerCase(second.getName().charAt(0));

            return Character.compare(left, right);
        }
    }
}

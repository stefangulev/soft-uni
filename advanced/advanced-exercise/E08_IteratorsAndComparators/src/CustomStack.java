import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CustomStack implements Iterable<Integer> {

    private List<Integer> elements;


    public CustomStack() {
        this.elements = new ArrayList<>();
    }

    public void push(int... element) {
        for (int i = 0; i < element.length ; i++) {
            this.elements.add(element[i]);
        }
    }

    public class StackIterator implements Iterator<Integer> {

        private int index = elements.size() - 1;
        @Override
        public boolean hasNext() {
            return index >= 0 ;
        }

        @Override
        public Integer next() {
            int current = elements.get(index);
            index--;
            return current;
        }
    }

    public void pop () {
        try {
            if (this.elements.size() == 0) {
                throw new NullPointerException("No elements");
            } else {
                elements.remove(elements.size() - 1);

            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @Override
    public Iterator<Integer> iterator() {
        return new StackIterator();
    }
}

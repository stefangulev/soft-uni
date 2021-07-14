import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListIterator implements Iterable<String> {
    private List<String> list;

   private int index = 0;
    private int initialIndex = 0;


    public ListIterator(List<String> list) {
        this.list = list;
    }

    public class ListIteratorIterator implements Iterator<String> {


        @Override
        public boolean hasNext() {
            return initialIndex < list.size();
        }

        @Override
        public String next() {
            String current = list.get(initialIndex);
            initialIndex++;
            return  current;
        }

    }

    public boolean move() {
        if (hasNext()) {
            index++;
            return true;
        } else {
            return false;
        }
    }

    public boolean hasNext() {
        return this.index < list.size() -1;
    }
    public void print() {
      try {
          if (this.list.size() != 0) {
              System.out.println(this.list.get(index));
          } else {
              throw new NullPointerException("Invalid Operation!");
          }
      } catch (NullPointerException ex) {
          System.out.println(ex.getMessage());
      }
    }

    public void printAll() {

        if (this.initialIndex == this.list.size()) {
           this.initialIndex = 0;
        }
        try {
            if (this.list.size() != 0) {
                while (iterator().hasNext()) {
                    System.out.print(iterator().next() + " ");
                }
                System.out.println();
            } else {
                throw new NullPointerException("Invalid Operation!");
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @Override
    public Iterator<String> iterator() {
        return new ListIteratorIterator();
    }
}

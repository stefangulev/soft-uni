package GenericBox;

import java.util.List;

public class GenericBox<T extends Comparable<T>> implements Comparable<GenericBox<T>> {

   private T element;

   public GenericBox(T element) {
       this.element = element;
   }

    public static <T> void swapIndexes (List<T> list, int firstIndex, int secondIndex) {
        T firstItem = list.get(firstIndex);
        T secondItem = list.get(secondIndex);

        list.add(secondIndex, firstItem);
        list.remove(secondIndex + 1);
        list.add(firstIndex, secondItem);
        list.remove(firstIndex + 1);

    }

    public static <T extends Comparable<T>> int compareElements (List<GenericBox<T>> list, GenericBox<T> elementToCompare) {
       int count = 0;

        for (GenericBox<T> t : list) {
            if (elementToCompare.compareTo(t) < 0) {
                count++;
            }
        }

        return count;
    }





    @Override
    public String toString() {
        return element.getClass().getName() +": " + element;
    }


    @Override
    public int compareTo(GenericBox<T> other) {
        return this.element.compareTo(other.element);
    }
}

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


public class CustomList<T extends Comparable<T>> {

    int initialCapacity = 4;
    Object[] arr;
    int size;

    public CustomList() {
        this.arr = new Object[initialCapacity];
    }


    public void add(T element) {

        arr[size] = element;
        size++;


        if (size == arr.length) {
            Object[] newArray = grow(arr);
            this.arr = newArray;

        }
    }

    public T remove (int index) {
        Object current;
        if (indexVerifier(index)) {
            current = this.arr[index];
            this.arr[index]= this.arr[index + 1];
            for (int i = index + 1; i < size - 1; i++) {
                this.arr[i] = this.arr[i + 1];
            }

            arr[size - 1] = null;

            size--;

            if (size == arr.length /2) {
                Object[] newArray = shrink(this.arr);
                this.arr = newArray;
            }

            return (T) current;
        }

        return null;
    }

    private Object[] grow(Object[] arr) {
        Object[] newArray = new Object[this.arr.length* 2];
        System.arraycopy(arr, 0, newArray, 0, arr.length);
        return newArray;
    }

    private Object[] shrink(Object[] arr) {
        Object[] newArray = new Object[this.arr.length/2];

        System.arraycopy(this.arr, 0, newArray, 0, newArray.length);

        return newArray;
    }



    public boolean indexVerifier(int index) {
        if (index < 0 || index > this.size) {
            return false;
        } else {
            return true;
        }

    }

    public boolean contains(T element) {
        for (Object o : arr) {
            if (o.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public T get(Object[] arr, int i) {


        Object o = arr[i];
        T item = (T) o;

        return item;
    }

    public int  countGreaterThan (T element) {
        int count = 0;

            for (int i = 0; i < this.size; i++) {

                    if (element.compareTo(get(this.arr, i)) < 0) {
                        count++;

                }
            }

        return count;
    }

    public void swap(int firstIndex, int secondIndex) {
        if (indexVerifier(firstIndex) && indexVerifier(secondIndex)) {
            Object firstElement = this.arr[firstIndex];
            Object secondElement = this.arr[secondIndex];

            this.arr[firstIndex] = secondElement;
            this.arr[secondIndex] = firstElement;
        }
    }

    public T getMax() {
        return Arrays.stream(this.arr).limit(this.size).map(e -> (T) e).max(Comparator.comparing(e-> e)).orElse(null
        );
    }
    public T getMin() {
        return Arrays.stream(this.arr).limit(this.size).map(e -> (T) e).min(Comparator.comparing(e-> e)).orElse(null
        );
    }

    public void print() {
        for (int i = 0; i < this.size; i++) {
            System.out.println(arr[i]);

        }
    }

    public void sort() {
        for (int i = 0; i < this.size ; i++) {
            T element = this.get(arr,i);
            for (int j = 0; j <this.size - 1; j++) {
                if (element.compareTo(this.get(arr, j)) < 0) {
                    this.swap(i, j);
                }
            }
        }
        
    }









}

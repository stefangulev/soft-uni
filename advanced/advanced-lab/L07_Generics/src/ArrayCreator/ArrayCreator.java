package ArrayCreator;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayCreator<T> {

    @SuppressWarnings("unchecked")
    public static <T> T[] create(int length, T element) {

        T[] array = (T[]) new Object[length];

        Arrays.fill(array, element);

        return array;

    }

    @SuppressWarnings("unchecked")
    public static <T> T[] create (Class<T> clazz,  int length, T element) {
        Object arr = Array.newInstance(clazz, length);

        return (T[])arr;
    }
}

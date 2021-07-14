package ListUtils;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> nums = Arrays.asList(1 ,5 ,6 ,7 ,10, -12);

        System.out.println(ListUtils.getMin(nums));
        System.out.println(ListUtils.getMax(nums));
    }
}

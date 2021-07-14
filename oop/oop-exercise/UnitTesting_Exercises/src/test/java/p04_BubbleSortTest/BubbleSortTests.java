package p04_BubbleSortTest;

import org.junit.Assert;
import org.junit.Test;

public class BubbleSortTests {

    @Test
    public void testIfSortsCorrectly() {
        int[] randomNums = {1,6,5,4,2,3};
        int[] sortedNums = {1,2,3,4,5,6};
        Bubble.sort(randomNums);
        for (int i = 0; i < randomNums.length; i++) {
            Assert.assertEquals(randomNums[i], sortedNums[i]);
        }
    }


}

package p05_CustomerLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import p05_CustomLinkedList.CustomLinkedList;

public class CustomLinkedListTests {

    CustomLinkedList<String> list;


    @Test(expected = IllegalArgumentException.class)
    public void testGetWithIndexOutOfBounds() {
        this.list = createList(3);
        this.list.get(3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetWithNegativeIndex() {
        this.list = createList(3);
        this.list.get(-2);
    }

    @Test
    public void testGetWithValidIndex() {
        this.list = createList(3);
       String elementFromList = this.list.get(1);
        Assert.assertEquals("element1", elementFromList);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetWithIndexOutOfBounds() {
        this.list = createList(4);
        this.list.set(5, "testElement");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetWithNegativeIndex() {
        this.list = createList(4);
        this.list.set(-1, "testElement");
    }

    @Test
    public void testSetWithValidIndex() {
        this.list = createList(4);
        list.set(1, "testElement");
        String elementFromList = this.list.get(1);
        Assert.assertEquals("testElement", elementFromList);

    }

    @Test
    public void testAddInFilledList() {
        this.list = createList(3);
        list.add("newElement");
        String addedElement = list.get(3);
        Assert.assertEquals( "newElement", addedElement);
    }
    @Test
    public void testAddInEmptyList() {
        this.list = new CustomLinkedList<>();
        list.add("newElement");
        String addedElement = list.get(0);
        Assert.assertEquals( "newElement", addedElement);
    }


    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtWithIndexOutOfBounds() {
        this.list = createList(3);
        list.removeAt(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtWithNegativeIndex() {
        this.list = createList(3);
        list.removeAt(-1);
    }

    @Test
    public void testRemoveAtWithValidIndex() {
        this.list = createList(3);
        String removed = list.removeAt(2);
        Assert.assertEquals("element2", removed);
    }

    @Test
    public void testRemoveSpecificItem() {
        this.list = createList(3);
        int indexOfFailedRemove = list.remove("item that does not exist");
        Assert.assertEquals(-1, indexOfFailedRemove, 0);
        int indexOfRemoved = list.remove("element1");
        Assert.assertEquals(1, indexOfRemoved, 0);
    }

    @Test
    public void testIndexOf() {
        this.list = createList(3);
        int indexOfNonExistentItem = list.indexOf("item that does not exist");
        Assert.assertEquals(-1, indexOfNonExistentItem, 0);
        int indexOfExistingItem = list.indexOf("element1");
        Assert.assertEquals(1, indexOfExistingItem, 0);
    }

    @Test
    public void testContains() {
        this.list = createList(3);
        Assert.assertFalse(list.contains("item that does not exist"));
        Assert.assertTrue(list.contains("element2"));
    }



    public CustomLinkedList<String> createList(int n) {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add("element" +i);
        }
        return list;
    }

}

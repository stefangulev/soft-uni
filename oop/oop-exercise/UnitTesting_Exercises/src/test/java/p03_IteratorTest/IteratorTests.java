package p03_IteratorTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class IteratorTests {

    ListIterator iterator;

    @Before
    public void initializeObject() throws OperationNotSupportedException {
        this.iterator = new ListIterator("first", "second", "third", "fourth");
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorAcceptsNullArgument() throws OperationNotSupportedException {
        this.iterator = new ListIterator(null);
    }

    @Test
    public void testHasNext() {
        iterator.move();
        Assert.assertTrue(iterator.hasNext());
        iterator.move();
        Assert.assertTrue(iterator.hasNext());
        iterator.move();
        Assert.assertFalse(iterator.hasNext());

    }

    @Test
    public void testMove() {
        iterator.move();
        Assert.assertTrue(iterator.hasNext());
        iterator.move();
        Assert.assertTrue(iterator.hasNext());
        iterator.move();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintOnEmptyCollection() throws OperationNotSupportedException {
        this.iterator = new ListIterator();
        iterator.print();
    }

    @Test
    public void testPrint() {
        iterator.move();
        iterator.move();
        String element = iterator.print();
        Assert.assertEquals("third", element);
    }


}

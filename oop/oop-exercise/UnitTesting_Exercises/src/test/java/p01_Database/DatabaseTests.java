package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import javax.xml.crypto.Data;

public class DatabaseTests {

    Database database;
    final Integer[] supportedArray = {1,2,3,4};
    final Integer[] tooSmallArray = new Integer[0];
    final Integer[] tooBigArray = new Integer[17];
    @Test
    public void testConstructorCreatesValidObject() throws OperationNotSupportedException {

        this.database = new Database(supportedArray);

        Assert.assertEquals(4, database.getElements().length);
        Assert.assertArrayEquals(supportedArray, this.database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfConstructorFailsWhenArgumentsOverflowCap() throws OperationNotSupportedException {
        this.database = new Database(tooBigArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfConstructorFailsWhenArgumentsAreLessThanMinimum() throws OperationNotSupportedException {
        this.database = new Database(tooSmallArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddOperationThrowsExceptionFromNullArguments() throws OperationNotSupportedException {
        this.database = new Database(supportedArray);
        this.database.add(null);
    }

    @Test
    public void testIfDatabaseAddsNewElementLast() throws OperationNotSupportedException {
       this.database = new Database(supportedArray);
       int element = 111;
        database.add(element);
        int lastElementInDatabase = database.getElements()[database.getElements().length - 1];
        Assert.assertEquals(element, lastElementInDatabase);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfRemoveOperationThrowsExIfDatabaseIsEmpty() throws OperationNotSupportedException {
        database = new Database(supportedArray);
        for (int i = 0; i < supportedArray.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testIfRemoveOperationRemovesElementsOnlyOnLastIndex() throws OperationNotSupportedException {
        this.database = new Database(supportedArray);
        Integer[] testArray = this.database.getElements();
        database.remove();
        Assert.assertEquals(testArray[testArray.length-2], database.getElements()[database.getElements().length -1]);
    }



}

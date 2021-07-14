package p02_ExtendedDatabase;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

public class ExtendedDatabaseTest {

    private Database database;

    private final int UNUSED_ID = 161;
    private final String UNUSED_NAME = "Peshoslav";

    Person[] personArray = {new Person(1, "First"), new Person(2, "Second"),
            new Person(3, "Third"), new Person(4, "Fourth")};

    @Before
    public void initializeDatabase() throws OperationNotSupportedException {
        this.database = new Database(personArray);
    }

    @Test
    public void testConstructorCreatesValidObject() throws OperationNotSupportedException {
        Assert.assertEquals(4, database.getElements().length);
        Assert.assertArrayEquals(personArray, this.database.getElements());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfConstructorFailsWhenArgumentsOverflowCap() throws OperationNotSupportedException {
        this.database = new Database(new Person[20]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfConstructorFailsWhenArgumentsAreLessThanMinimum() throws OperationNotSupportedException {
        this.database = new Database(new Person[0]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddOperationThrowsExceptionFromNullArguments() throws OperationNotSupportedException {
        this.database.add(null);
    }

    @Test
    public void testIfDatabaseAddsNewElementLast() throws OperationNotSupportedException {
        Person person = new Person(231, "Gencho");
        database.add(person);
        Person lastElementInDatabase = database.getElements()[database.getElements().length - 1];
        Assert.assertEquals(person, lastElementInDatabase);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfRemoveOperationThrowsExIfDatabaseIsEmpty() throws OperationNotSupportedException {
        for (int i = 0; i < personArray.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testIfRemoveOperationRemovesElementsOnlyOnLastIndex() throws OperationNotSupportedException {
        Person[] testArray = this.database.getElements();
        database.remove();
        Assert.assertEquals(testArray[testArray.length-2], database.getElements()[database.getElements().length -1]);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfFindIdThrowsExWhenNoSuchIdIsPresent() throws OperationNotSupportedException {
        Person person = database.findById(UNUSED_ID);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testIfFindByUsernameThrowsExWhenNoSuchNameIsPresent() throws OperationNotSupportedException {
        Person person = database.findByUsername(UNUSED_NAME);
    }


    @Test(expected = OperationNotSupportedException.class)
    public void testIfFindByUsernameThrowsExWhenParameterIsNull() throws OperationNotSupportedException {
        Person person = database.findByUsername(null);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testIfFindByUsernameArgumentsAreCasesSensitive() throws OperationNotSupportedException {
        database.findByUsername("FIRST");
    }




}

package computers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComputerTest {

   Computer computer;
   Part part;
   Part part1;


   @Before
    public void intilializeObject() {
       this.computer = new Computer("DELL");
       this.part = new Part("processor", 12.00);
       this.part1 = new Part("video card", 15.00);
   }

   @Test
    public void testIfConstructorCreatesCorrectObject() {
       Assert.assertEquals("DELL", this.computer.getName());
       Assert.assertEquals(0, this.computer.getParts().size());
   }
   @Test(expected = IllegalArgumentException.class)
    public void testIfComputerCanBeCreatedWithInvalidName() {
       Computer computerInvalid = new Computer(null);
   }

   @Test
    public void testIfGetNameReturnsTheCorrectResult() {
       Assert.assertEquals("DELL", this.computer.getName());
   }

   @Test
    public void checkIfGetPartsReturnsTheCorrectResult() {
       Assert.assertEquals(0, this.computer.getParts().size());
       this.computer.addPart(part);
       Assert.assertEquals(1, this.computer.getParts().size());

   }
   @Test
    public void checkIfGetTotalPriceReturnsTheCorrectResult() {
       this.computer.addPart(part);
       this.computer.addPart(part1);
       double totalPrice = part.getPrice() + part1.getPrice();
       Assert.assertEquals(totalPrice, computer.getTotalPrice(), 0);
   }

    @Test
    public void checkIfAddPartReturnsTheCorrectResult() {
        Assert.assertEquals(0, this.computer.getParts().size());
        this.computer.addPart(part);
        Assert.assertEquals(1, this.computer.getParts().size());
        this.computer.addPart(part1);
        Assert.assertEquals(2, this.computer.getParts().size());
    }
    @Test(expected = IllegalArgumentException.class)
    public void checkIfAddPartThrowsExcWithNullArgument() {
       this.computer.addPart(null);
    }

    @Test
    public void checkIfRemovePartReturnsCorrect() {
       this.computer.addPart(part);
       this.computer.addPart(part1);
       Assert.assertTrue(this.computer.removePart(part1));
       Assert.assertEquals(1, this.computer.getParts().size());
       Assert.assertFalse(this.computer.removePart(part1));
        Assert.assertEquals(1, this.computer.getParts().size());
    }
    @Test
    public void checkIfGetPartRetrunsCorrectResult() {
        this.computer.addPart(part);
        this.computer.addPart(part1);
        Part partFromComputer = this.computer.getPart("processor");
        Assert.assertEquals(partFromComputer.getPrice(), part.getPrice(), 0);
        Part partFromComputer1 = this.computer.getPart("non-existing");
        Assert.assertNull(partFromComputer1);


    }


}
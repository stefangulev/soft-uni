package halfLife;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTests {
    Player player;
    Gun gun;
    Gun gun2;

    @Before
    public void initializeObject() {
        player = new Player("Pesho", 100);
        gun = new Gun("Makarov", 100);
        gun2 = new Gun("Kalashnikov", 50);

    }

    @Test
    public void testConstructorCreatesCorrectObject() {
        Assert.assertEquals("Pesho", this.player.getUsername());
        Assert.assertEquals(100, this.player.getHealth());
        Assert.assertTrue(this.player.getGuns().isEmpty());
    }
    @Test(expected = NullPointerException.class)
    public void testIfInvalidNameThrowsException() {
        Player newPlayer = new Player(null, 100);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIfInvalidHealthThrowsException() {
        Player newPlayer = new Player("Tom", -12);
    }
    //possibly test setName, setHealth.

    @Test
    public void testGetNameReturnsCorrectResult() {
        Assert.assertEquals("Pesho", this.player.getUsername());
    }
    @Test
    public void testGetHealthReturnsCorrectResult() {
        Assert.assertEquals(100, this.player.getHealth());
    }
    @Test
    public void testGetGunsReturnsCorrectResult() {
        Assert.assertTrue(player.getGuns().isEmpty());
        player.addGun(gun);
        Assert.assertEquals(1, player.getGuns().size());
    }
    //takedamage
    @Test (expected = NullPointerException.class)
    public void testIfAddGunThrowsExcWithNullArgument() {
        player.addGun(null);
    }
    @Test
    public void testIfAddGunAddsCorrectGun() {
        player.addGun(gun);
        Assert.assertEquals(1, player.getGuns().size());
        Gun testGun = player.getGun(gun.getName());
        Assert.assertNotNull(testGun);
        Assert.assertEquals(gun.getName(), testGun.getName());
    }

    @Test
    public void testIfGetGunReturnsCorrectGun() {
        player.addGun(gun);
        Assert.assertEquals(1, player.getGuns().size());
        Gun testGun = player.getGun(gun.getName());
        Assert.assertNotNull(testGun);
        Assert.assertEquals(gun.getName(), testGun.getName());
    }
    @Test
    public void testIfRemoveGunDoesTheAction() {
        player.addGun(gun);
        player.addGun(gun2);
        Assert.assertTrue(player.removeGun(gun2));
        Assert.assertEquals(1, player.getGuns().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testIfDeadPlayerTakesDamage() {
        Player deadPlayer = new Player("Ivan", 0);
        deadPlayer.takeDamage(10);
    }
    @Test
    public void testIfTakeDamageReducesHealth() {
        player.takeDamage(10);
        Assert.assertEquals(90, player.getHealth());
    }
    @Test
    public void testIfHealthCanGetToANegativeValue() {
        player.takeDamage(110);
        Assert.assertEquals(0, player.getHealth());
    }





}

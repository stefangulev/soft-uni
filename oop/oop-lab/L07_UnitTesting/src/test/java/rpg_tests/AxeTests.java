package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class AxeTests {

    private final int GOOD_AXE_ATTACK = 10;
    private final int GOOD_AXE_DURABILITY = 10;
    private final int BROKEN_AXE_DURABILITY = 0;
    private final int DUMMY_HEALTH = 10;
    private final int DUMMY_XP = 10;

    public Axe axe;
    public Dummy dummy;

    @Before
    public void initializeObjects() {
        this.axe = new Axe(GOOD_AXE_ATTACK, GOOD_AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_XP);
    }


    @Test
    public void testIfWeaponLosesDurabilityAfterEachAttack() {
        axe.attack(dummy);
        Assert.assertEquals(9, axe.getDurabilityPoints(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void testIfAttackingWithABrokenWeaponWorks() {
        Axe axe = new Axe (GOOD_AXE_ATTACK, BROKEN_AXE_DURABILITY);
        axe.attack(dummy);
    }

}

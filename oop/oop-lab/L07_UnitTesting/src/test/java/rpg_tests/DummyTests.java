package rpg_tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

public class DummyTests {

   private final int GOOD_DUMMY_HEALTH = 10;
    private final int GOOD_DUMMY_XP = 10;
    private final int GOOD_AXE_ATTACK = 10;
    private final int GOOD_AXE_DURABILITY = 10;
    private final int DEAD_DUMMY_HEALTH = 0;

    public Dummy dummy;
    public Axe axe;

    @Before
    public void initializeObjects() {
        this.axe = new Axe(GOOD_AXE_ATTACK, GOOD_AXE_DURABILITY);
        this.dummy = new Dummy(GOOD_DUMMY_HEALTH, GOOD_DUMMY_XP);
    }


    @Test
    public void testIfDummyLosesHealthWhenAttacked() {
        dummy.takeAttack(axe.getAttackPoints());
        Assert.assertEquals(GOOD_DUMMY_HEALTH - axe.getAttackPoints(), dummy.getHealth(), 0);
    }

    @Test(expected = IllegalStateException.class)
    public void testIfDeadDummyThrowsExceptionWhenAttacked() {
        Dummy dummy = new Dummy(0, GOOD_DUMMY_XP);
        dummy.takeAttack(axe.getAttackPoints());
    }

    @Test
    public void testIfDeadDummyGivesXP() {
        Dummy dummy = new Dummy(DEAD_DUMMY_HEALTH, GOOD_DUMMY_XP);
        int experienceGiven = dummy.giveExperience();
        Assert.assertEquals(GOOD_DUMMY_XP, experienceGiven, 0);
    }

    @Test(expected = IllegalStateException.class)
    public void testIfAliveDummyCantGiveXP() {
        Dummy dummy = new Dummy(GOOD_DUMMY_HEALTH, GOOD_DUMMY_XP);
        dummy.giveExperience();
    }
}

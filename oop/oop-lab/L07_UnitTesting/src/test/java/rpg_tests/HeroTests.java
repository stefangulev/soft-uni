package rpg_tests;

import interfaces.Target;
import interfaces.Weapon;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Axe;
import rpg_lab.Dummy;
import rpg_lab.Hero;

import java.lang.reflect.Modifier;

public class HeroTests {

    private final int TARGET_XP = 5;



    @Test
    public void testIfAttackGainsExperienceWhenTargetIsDead() {

        Weapon fakeWeapon = Mockito.mock(Weapon.class);
        Target fakeTarget = Mockito.mock(Target.class);
        Mockito.when(fakeTarget.isDead()).thenReturn(true);
        Mockito.when(fakeTarget.giveExperience()).thenReturn(TARGET_XP);
        Hero hero = new Hero("Gara Dembele", fakeWeapon);
        hero.attack(fakeTarget);
        Assert.assertEquals(TARGET_XP, hero.getExperience(), 0);

    }
}

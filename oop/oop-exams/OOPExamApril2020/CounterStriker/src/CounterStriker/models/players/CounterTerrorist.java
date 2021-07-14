package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

public class CounterTerrorist extends PlayerImpl {

    public CounterTerrorist(String username, int health, int armour, Gun gun) {
        super(username, health, armour, gun);
    }
}

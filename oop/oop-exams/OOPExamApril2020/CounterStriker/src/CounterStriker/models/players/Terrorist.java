package CounterStriker.models.players;

import CounterStriker.models.guns.Gun;

public class Terrorist extends PlayerImpl {

    public Terrorist(String username, int health, int armour, Gun gun) {
        super(username, health, armour, gun);
    }
}

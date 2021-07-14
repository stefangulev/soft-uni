package CounterStriker.models.players;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.guns.Gun;

public abstract class PlayerImpl implements Player {

    private String username;
    private int health;
    private int armor;
    private boolean isAlive;
    private Gun gun;

    protected PlayerImpl(String username, int health, int armour, Gun gun) {
        setUsername(username);
        setHealth(health);
        isAlive = true;
        setArmor(armour);
        setGun(gun);

    }

    protected void setUsername(String username) {
        if(username == null || username.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_NAME);
        }
        this.username = username;
    }
    protected void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_HEALTH);
        }
        this.health = health;
    }
    protected void setArmor(int armor) {
        if (armor < 0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_ARMOR);
        }
        this.armor = armor;
    }

    protected void setAlive() {
        if(this.health > 0) {
            this.isAlive = true;
            return;
        }
        this.isAlive = false;
    }

    protected void setGun(Gun gun) {
        if (gun == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_GUN);
        }
        this.gun = gun;
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getArmor() {
        return this.armor;
    }

    @Override
    public Gun getGun() {
        return this.gun;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void takeDamage(int points) {
        if (this.getArmor() <= 0) {
            int reducedHealth = getHealth() - points;
            this.health = reducedHealth;
            setAlive();
            return;
        }
        int reducedAmour = getArmor() - points;
        this.armor = reducedAmour;

    }
}

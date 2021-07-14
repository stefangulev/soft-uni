package CounterStriker.models.guns;

public class Pistol extends GunImpl {

    public Pistol(String name, int bulletsCount) {
        super(name, bulletsCount);
    }


    @Override
    public int fire() {
        if (super.getBulletsCount() >= 1) {
            int newBulletCount = super.getBulletsCount() - 1;
            super.setBulletsCount(newBulletCount);
            return 1;
        }
        return 0;
    }
}

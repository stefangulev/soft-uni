package robotService.models.robots.interfaces;

public class Housekeeper extends BaseRobot {

    public Housekeeper(String name, int energy, int happiness, int produceTime) {
        super(name, energy, happiness, produceTime);
    }

    @Override
    public String toString() {
        return String.format(" Robot type: %s - %s - Happiness: %d - Energy: %d",
                this.getClass().getSimpleName(), this.getName(),this.getHappiness(), this.getEnergy());
    }

}

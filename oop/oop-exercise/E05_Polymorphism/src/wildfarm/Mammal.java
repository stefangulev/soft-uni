package wildfarm;

public abstract class Mammal extends Animal {
    private String livingRegion;

    protected Mammal(String animalType,String animalName, Double animalWeight, String livingRegion) {
        super(animalType, animalName, animalWeight);
        this.livingRegion = livingRegion;
    }


    public String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %d]", getAnimalType(),
                getAnimalName(), format.format(getAnimalWeight()), getLivingRegion(), getFoodEaten());
    }

}

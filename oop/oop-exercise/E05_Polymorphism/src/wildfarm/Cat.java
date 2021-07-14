package wildfarm;

public class Cat extends Felime {

    private String breed;

    protected Cat(String animalType, String animalName, Double animalWeight, String livingRegion, String breed) {
        super(animalType, animalName, animalWeight, livingRegion);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public void eatFood(Food food) {
        makeSound();
        setFoodEaten(this.getFoodEaten() + food.getQuantity());
    }

    @Override
    public String toString() {
        return String.format("%s[%s, %s, %s, %s, %d]", getAnimalType(),
                getAnimalName(), this.getBreed(), format.format(getAnimalWeight()), getLivingRegion(), getFoodEaten());
    }
}

package animals;

public class Cat extends Animal {

    public Cat(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    protected String explainSelf() {
        return "I am " + super.getName() +" and my favourite food is " + super.getFavouriteFood() + System.lineSeparator() + "MEEOW";
    }
}

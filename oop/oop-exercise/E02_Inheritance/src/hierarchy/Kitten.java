package hierarchy;

public class Kitten extends Cat {

    private static final String KITTEN_GENDER = "Female";


    public Kitten(String name, int age, String gender) {
        super(name, age, KITTEN_GENDER);
    }


    @Override

    public String produceSound() {
        return "Meow";
    }
}

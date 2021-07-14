package animals;

public class Main {
    public static void main(String[] args) {

        Animal cat = new Cat("Mark", "Whiskas");
        Animal dog = new Dog("Larry", "Food");
        System.out.println(cat.explainSelf());
        System.out.println(dog.explainSelf());
    }
}

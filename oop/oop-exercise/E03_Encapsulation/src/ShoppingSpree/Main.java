package ShoppingSpree;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("levski", 104343.3);
        Person gosho = new Person("gosho",  23.5);

        gosho.buyProduct(product);

    }
}

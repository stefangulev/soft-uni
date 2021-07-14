package ShoppingSpree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    private void setMoney(double money) {
        if (money <0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = money;
    }

    private void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void buyProduct(Product product) {
            if (product.getCost() > this.money) {
                throw new IllegalArgumentException(String.format("%s can't afford %s", this.getName(), product.getName()));
            } else {
                setMoney(this.money - product.getCost());
                this.products.add(product);
            }

    }

    @Override
    public String toString() {
        List<String> productNames = new ArrayList<>();
        if (!this.products.isEmpty()) {
            this.products.forEach(e -> productNames.add(e.getName()));
        }
        String result = this.getName() + " - ";
         return result+= this.products.isEmpty() ? "Nothing bought" : productNames.toString().replaceAll("[\\[\\]]", "");
    }
}

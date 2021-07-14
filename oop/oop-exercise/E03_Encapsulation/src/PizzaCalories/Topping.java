package PizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String.format("%s weight should be in the range [1..50].", this.toppingType));
        }
        this.weight = weight;
    }

    private void setToppingType(String toppingType) {
        if (!toppingType.equals("Meat") && !toppingType.equals("Veggies") && !toppingType.equals("Cheese") && !toppingType.equals("Sauce")) {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }
        this.toppingType = toppingType;
    }

    public double calculateCalories() {
        double caloriesTopping = 0;

        switch (this.toppingType) {
            case "Meat":
                caloriesTopping = 1.2;
                break;
            case "Cheese":
                caloriesTopping = 1.1;
                break;
            case "Veggies":
                caloriesTopping = 0.8;
                break;
            case "Sauce":
                caloriesTopping = 0.9;
                break;
        }
        return (2 * this.weight) * caloriesTopping;
    }
}

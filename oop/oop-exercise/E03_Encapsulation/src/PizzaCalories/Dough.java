package PizzaCalories;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);

    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 200) {
            throw new IllegalArgumentException("Dough weight should be in the range of [1..200].");
        }
        this.weight = weight;
    }

    private void setBakingTechnique(String bakingTechnique) {
        if (!bakingTechnique.equals("Crispy") && !bakingTechnique.equals("Chewy") && !bakingTechnique.equals("Homemade")) {
            throwDoughException();
        }
        this.bakingTechnique = bakingTechnique;
    }

    private void setFlourType(String flourType) {
        if (!flourType.equals("White") && !flourType.equals("Wholegrain")) {
            throwDoughException();
        }
        this.flourType = flourType;
    }

    public double calculateCalories() {
        double caloriesFromType = 0;
        double caloriesFromTechnique = 0;
        switch (this.flourType) {
            case "White":
                caloriesFromType = 1.5;
                break;
            case "Wholegrain":
                caloriesFromType = 1.0;
                break;
        }
        switch (this.bakingTechnique) {
            case "Crispy":
                caloriesFromTechnique = 0.9;
                break;
            case "Chewy":
                caloriesFromTechnique = 1.1;
                break;
            case "Homemade":
                caloriesFromTechnique = 1.0;
                break;
        }

        return (2 * this.weight) * caloriesFromTechnique * caloriesFromType;
    }

    public void throwDoughException() {
        throw new IllegalArgumentException("Invalid type of dough.");
    }
}

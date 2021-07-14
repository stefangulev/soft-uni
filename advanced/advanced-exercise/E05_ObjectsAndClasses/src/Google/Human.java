package Google;

import com.sun.source.tree.LiteralTree;

import java.util.ArrayList;
import java.util.List;

public class Human {
    private String humanName;
    private Company company = new Company();
    private Car car = new Car();
    private List<Parent> parents = new ArrayList<>();
    private List<Child> children = new ArrayList<>();
    private List<Pokemon> pokemons = new ArrayList<>();

    public Human (String humanName) {
        this.humanName = humanName;
    }

    public void setCompany (Company company) {
       this.company = company;
    }

    public void setPokemons(Pokemon pokemon) {
        this.pokemons.add(pokemon);

    }
    public void setParents(Parent parent) {
        this.parents.add(parent);
    }
    public  void setChildren (Child child) {
        this.children.add(child);
    }
    public void setCar(Car car) {
        this.car = car;
    }

    public String getPokemonList() {
        String result = "";
        if (!this.pokemons.isEmpty()) {
            for (Pokemon pokemon : pokemons) {
                result += String.format("%s %s %n", pokemon.getPokemonName(), pokemon.getPokemonType());
            }
        }
        return result;
    }
    public String getParentList() {
        String result = "";
        if (!this.parents.isEmpty()) {
            for (Parent parent : parents) {
                result += String.format("%s %s %n", parent.getParentName(), parent.getBirthday());
            }
        }
        return result;
    }

    public String getChildrenList() {
        String result = "";
        if (!this.children.isEmpty()) {
            for (Child child : children) {
                result += String.format("%s %s %n", child.getChildName(), child.getChildBirthday());
            }
        }
        return result;
    }


    @Override
    public String toString() {
        return this.humanName + '\n' + this.company.getCompany() + '\n' + this.car.getCar() + '\n' + "Pokemon:"
                + '\n' + getPokemonList() + "Parents:" + '\n'
                + getParentList() + "Children:" + '\n'
                + getChildrenList();
    }
}

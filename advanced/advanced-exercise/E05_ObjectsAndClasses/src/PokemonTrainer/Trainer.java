package PokemonTrainer;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String name;
    private int badges;
    private List<Pokemon> pokemonCollection;

    public Trainer(String name, String pokemonName, String element, int health) {
        this.name = name;
        this.pokemonCollection = new ArrayList<>();
        Pokemon pokemon = new Pokemon(pokemonName, element, health);
        pokemonCollection.add(pokemon);
        this.badges = 0;
    }

    public void addPokemonToList(Pokemon pokemon) {
        this.pokemonCollection.add(pokemon);
    }

    public void checkElementInCollection(String element) {
        if (!pokemonCollection.isEmpty()) {
            for (Pokemon pokemon : pokemonCollection) {
                if (pokemon.getElement().equals(element)) {
                    this.badges++;
                    return;
                }
            }
        for (Pokemon pokemon : pokemonCollection) {
            pokemon.reduceHealth();
            if (pokemon.getHealth() < 1) {
                this.pokemonCollection.remove(pokemon);
                if (pokemonCollection.isEmpty()) {
                    return;
                }
            }
        }

    }

}

    public int getBadges() {
        return this.badges;
    }

    public int getCollectionSize() {
        return this.pokemonCollection.size();
    }

    public String getName() {
        return this.name;
    }
}

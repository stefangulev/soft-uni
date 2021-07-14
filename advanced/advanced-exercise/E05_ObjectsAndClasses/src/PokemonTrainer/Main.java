package PokemonTrainer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        Map<String, Trainer> trainers = new LinkedHashMap<>();

        while (!input.equals("Tournament")) {

            String[] tokens = input.split("\\s+");
            String trainerName = tokens[0];
            String pokemonName = tokens[1];
            String pokemonElement = tokens[2];
            int pokemonHealth = Integer.parseInt(tokens[3]);

            Trainer trainer;

            if (trainers.containsKey(trainerName)) {
                Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
                trainers.get(trainerName).addPokemonToList(pokemon);
            } else {
                trainer = new Trainer(trainerName, pokemonName, pokemonElement, pokemonHealth);
                trainers.put(trainerName, trainer);
            }


            input = scan.nextLine();
        }

        String elementInput = scan.nextLine();

        while (!elementInput.equals("End")) {

            for (Map.Entry<String, Trainer> stringTrainerEntry : trainers.entrySet()) {
                stringTrainerEntry.getValue().checkElementInCollection(elementInput);

            }

            elementInput = scan.nextLine();
        }

        trainers.entrySet().stream().sorted((l , r) -> Integer.compare(r.getValue().getBadges(), l.getValue().getBadges()))
                .forEach( e-> System.out.println(String.format("%s %d %d", e.getValue().getName(), e.getValue().getBadges(), e.getValue().getCollectionSize())));

    }
}

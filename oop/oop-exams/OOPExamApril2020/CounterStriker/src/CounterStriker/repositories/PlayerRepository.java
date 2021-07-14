package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerRepository implements Repository<Player> {
    private List<Player> models;

    public PlayerRepository() {
        this.models = new ArrayList<>();
    }
    @Override
    public Collection<Player> getModels() {
        return this.models;
    }

    @Override
    public void add(Player model) {
        if (model == null) {
            throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_REPOSITORY);
        }
        models.add(model);
    }

    @Override
    public boolean remove(Player model) {
        for (int i = 0; i < models.size() ; i++) {
            if (models.get(i).getUsername().equals(model.getUsername())) {
                return models.remove(models.get(i));
            }
        }
        return false;
    }

    @Override
    public Player findByName(String name) {
        for (Player model : models) {
            if (model.getUsername().equals(name)) {
                return model;
            }
        }
        return null;
    }
}

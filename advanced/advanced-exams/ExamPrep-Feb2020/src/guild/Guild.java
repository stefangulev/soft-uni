package guild;

import java.util.*;

public class Guild {

    private String name;
    private int capacity;
    private List<Player> roster;


    public Guild(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.roster = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (this.roster.size() < this.capacity) {
            this.roster.add(player);
        }
    }

    public boolean removePlayer(String name) {
         return this.roster.removeIf(player -> player.getName().equals(name));
    }

    public void promotePlayer (String name) {
        for (Player player : this.roster) {
            if (player.getName().equals(name)) {
                player.setRank("Member");
                break;
            }
        }
    }
    public void demotePlayer (String name) {
        for (Player player : this.roster) {
            if (player.getName().equals(name)) {
                player.setRank("Trial");
                break;
            }
        }
    }

    public Player[] kickPlayersByClass (String clazz) {
       Player[] removed = this.roster.stream().filter(e -> e.getClazz().equals(clazz)).toArray(Player[]::new);
        for (Player player : removed) {
            this.roster.removeIf(player1 -> player1.getName().equals(player.getName()));
        }

       return removed;
    }

    public int count() {
        return this.roster.size();
    }

    public String report() {
        StringBuilder result = new StringBuilder();
        result.append("Players in the guild: %s:%n").append(this.name).append(":").append(System.lineSeparator());
        for (Player player : roster) {
            result.append(player).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

}


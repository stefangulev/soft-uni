package CounterStriker.models.field;

import CounterStriker.common.OutputMessages;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FieldImpl implements Field {
    @Override
    public String start(Collection<Player> players) {
        List<Player> terrorists = players.stream().filter(player -> player
                .getClass().getSimpleName().equals("Terrorist")).collect(Collectors.toList());
        List<Player> counterTerrorists = players.stream().filter(player -> player
                .getClass().getSimpleName().equals("CounterTerrorist")).collect(Collectors.toList());
        while (!checkIfTeamIsDead(terrorists) && !checkIfTeamIsDead(counterTerrorists)) {
            teamAttacks(terrorists, counterTerrorists);
            teamAttacks(counterTerrorists, terrorists);
        }
        if (checkIfTeamIsDead(terrorists)) {
            return OutputMessages.COUNTER_TERRORIST_WINS;
        } else {
            return OutputMessages.TERRORIST_WINS;
        }

    }


    protected void teamAttacks(List<Player> attacker, List<Player> defenders) {
        for (int i = 0; i < attacker.size(); i++) {
            if (!attacker.get(i).isAlive()) {
                i++;
                continue;
            }
            for (int j = 0; j < defenders.size(); j++) {
                if (defenders.get(j).isAlive()) {
                    defenders.get(j).takeDamage(attacker.get(i).getGun().fire());
                }
            }
        }
    }

    protected boolean checkIfTeamIsDead(Collection<Player> team) {
        boolean areAllMembersDead = true;
        for (Player player : team) {
            if (player.isAlive()) {
                areAllMembersDead = false;
                break;
            }

        }
        return areAllMembersDead;
    }
}

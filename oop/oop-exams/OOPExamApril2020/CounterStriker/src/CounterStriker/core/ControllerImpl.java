package CounterStriker.core;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.common.OutputMessages;
import CounterStriker.models.field.Field;
import CounterStriker.models.field.FieldImpl;
import CounterStriker.models.guns.Pistol;
import CounterStriker.models.guns.Rifle;
import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Terrorist;
import CounterStriker.repositories.GunRepository;
import CounterStriker.repositories.PlayerRepository;

import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private GunRepository guns;
    private PlayerRepository players;
    private Field field;

    public ControllerImpl() {
        this.guns = new GunRepository();
        this.players = new PlayerRepository();
        this.field = new FieldImpl();
    }

    @Override
    public String addGun(String type, String name, int bulletsCount) {
        if (type.equals("Pistol")) {
            Pistol pistol = new Pistol(name, bulletsCount);
            guns.add(pistol);
        } else if (type.equals("Rifle")) {
            Rifle rifle = new Rifle(name, bulletsCount);
            guns.add(rifle);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_GUN_TYPE);
        }
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_GUN, name);
    }

    @Override
    public String addPlayer(String type, String username, int health, int armor, String gunName) {
        if(guns.findByName(gunName) == null) {
            throw new NullPointerException(ExceptionMessages.GUN_CANNOT_BE_FOUND);
        }
        if (type.equals("Terrorist")) {
            Terrorist terrorist = new Terrorist(username, health, armor, guns.findByName(gunName));
            players.add(terrorist);
        } else if (type.equals("CounterTerrorist")) {
            CounterTerrorist counterTerrorist = new CounterTerrorist(username, health, armor, guns.findByName(gunName));
            players.add(counterTerrorist);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PLAYER_TYPE);
        }
        return String.format(OutputMessages.SUCCESSFULLY_ADDED_PLAYER, username);
    }

    @Override
    public String startGame() {
        return field.start(players.getModels());
    }

    @Override
    public String report() {
        return this.players.getModels().stream().sorted((l,r) -> {
            int result = l.getClass().getSimpleName().compareTo(r.getClass().getSimpleName());
            if (result == 0) {
                result = Integer.compare(r.getHealth(), l.getHealth());
                if (result == 0) {
                    result = l.getUsername().compareTo(r.getUsername());
                }
            }
            return result;
        }).map(player -> String.format("%s: %s", player.getClass().getSimpleName(), player.getUsername())
                + System.lineSeparator() + String.format("--Health: %d", player.getHealth()) +
                System.lineSeparator() + String.format("--Armor: %d", player.getArmor()) +
                System.lineSeparator() + String.format("--Gun: %s", player.getGun().getName()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}

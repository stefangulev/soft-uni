package com.example.football.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "players")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedDto {
    @XmlElement(name = "player")
    private List<PlayerSeedSingleDto> players;

    public List<PlayerSeedSingleDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerSeedSingleDto> players) {
        this.players = players;
    }
}

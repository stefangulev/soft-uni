package com.example.football.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlayerSeedSingleDto {
    @XmlElement(name = "first-name")
    private String firstName;
    @XmlElement(name = "last-name")
    private String lastName;
    @XmlElement
    private String email;
    @XmlElement(name = "birth-date")
    private String birthDate;
    @XmlElement
    private String position;
    @XmlElement(name = "town")
    private PlayerTownDto town;
    @XmlElement(name = "team")
    private PlayerTeamDto team;
    @XmlElement(name = "stat")
    private PlayerStatDto stat;

    @Size(min = 3)
    @NotBlank
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 3)
    @NotBlank
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Email
    @NotBlank
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @NotBlank
    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    @NotBlank
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @NotNull
    public PlayerTownDto getTown() {
        return town;
    }

    public void setTown(PlayerTownDto town) {
        this.town = town;
    }

    @NotNull
    public PlayerTeamDto getTeam() {
        return team;
    }

    public void setTeam(PlayerTeamDto team) {
        this.team = team;
    }

    @NotNull
    public PlayerStatDto getStat() {
        return stat;
    }

    public void setStat(PlayerStatDto stat) {
        this.stat = stat;
    }
}

package com.example.manchesterunitedfan.model.view;

import com.example.manchesterunitedfan.model.enums.NationalityEnum;
import com.example.manchesterunitedfan.model.enums.PositionEnum;

public class PlayerDetailsView {
    private String firstName;
    private String lastName;
    private Integer squadNumber;
    private String imgUrl;
    private PositionEnum position;
    private Integer age;
    private String description;
    private NationalityEnum nationality;

    public String getFirstName() {
        return firstName;
    }

    public PlayerDetailsView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PlayerDetailsView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getSquadNumber() {
        return squadNumber;
    }

    public PlayerDetailsView setSquadNumber(Integer squadNumber) {
        this.squadNumber = squadNumber;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public PlayerDetailsView setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public PlayerDetailsView setPosition(PositionEnum position) {
        this.position = position;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public PlayerDetailsView setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PlayerDetailsView setDescription(String description) {
        this.description = description;
        return this;
    }

    public NationalityEnum getNationality() {
        return nationality;
    }

    public PlayerDetailsView setNationality(NationalityEnum nationality) {
        this.nationality = nationality;
        return this;
    }
}

package com.example.manchesterunitedfan.model.service;

import com.example.manchesterunitedfan.model.enums.NationalityEnum;
import com.example.manchesterunitedfan.model.enums.PositionEnum;

import javax.validation.constraints.*;

public class AddPlayerServiceModel {
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

    public AddPlayerServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AddPlayerServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getSquadNumber() {
        return squadNumber;
    }

    public AddPlayerServiceModel setSquadNumber(Integer squadNumber) {
        this.squadNumber = squadNumber;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public AddPlayerServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public AddPlayerServiceModel setPosition(PositionEnum position) {
        this.position = position;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public AddPlayerServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddPlayerServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public NationalityEnum getNationality() {
        return nationality;
    }

    public AddPlayerServiceModel setNationality(NationalityEnum nationality) {
        this.nationality = nationality;
        return this;
    }
}

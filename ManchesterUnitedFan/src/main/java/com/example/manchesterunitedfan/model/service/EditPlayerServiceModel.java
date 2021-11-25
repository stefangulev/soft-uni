package com.example.manchesterunitedfan.model.service;

import com.example.manchesterunitedfan.model.enums.NationalityEnum;
import com.example.manchesterunitedfan.model.enums.PositionEnum;

public class EditPlayerServiceModel {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer squadNumber;
    private String imgUrl;
    private PositionEnum position;
    private Integer age;
    private String description;
    private NationalityEnum nationality;


    public Long getId() {
        return id;
    }

    public EditPlayerServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EditPlayerServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EditPlayerServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getSquadNumber() {
        return squadNumber;
    }

    public EditPlayerServiceModel setSquadNumber(Integer squadNumber) {
        this.squadNumber = squadNumber;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public EditPlayerServiceModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public EditPlayerServiceModel setPosition(PositionEnum position) {
        this.position = position;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public EditPlayerServiceModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public EditPlayerServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public NationalityEnum getNationality() {
        return nationality;
    }

    public EditPlayerServiceModel setNationality(NationalityEnum nationality) {
        this.nationality = nationality;
        return this;
    }
}

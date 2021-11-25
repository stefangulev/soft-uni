package com.example.manchesterunitedfan.model.binding;

import com.example.manchesterunitedfan.model.enums.NationalityEnum;
import com.example.manchesterunitedfan.model.enums.PositionEnum;

import javax.validation.constraints.*;

public class AddPlayerBindingModel {
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

    public AddPlayerBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotBlank
    public String getLastName() {
        return lastName;
    }

    public AddPlayerBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotNull
    @Positive
    public Integer getSquadNumber() {
        return squadNumber;
    }

    public AddPlayerBindingModel setSquadNumber(Integer squadNumber) {
        this.squadNumber = squadNumber;
        return this;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public AddPlayerBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    @NotNull
    public PositionEnum getPosition() {
        return position;
    }

    public AddPlayerBindingModel setPosition(PositionEnum position) {
        this.position = position;
        return this;
    }

    @NotNull
    @Min(15)
    @Max(45)
    public Integer getAge() {
        return age;
    }

    public AddPlayerBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public AddPlayerBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    public NationalityEnum getNationality() {
        return nationality;
    }

    public AddPlayerBindingModel setNationality(NationalityEnum nationality) {
        this.nationality = nationality;
        return this;
    }
}

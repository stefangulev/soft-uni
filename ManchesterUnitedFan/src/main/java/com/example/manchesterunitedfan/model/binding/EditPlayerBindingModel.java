package com.example.manchesterunitedfan.model.binding;

import com.example.manchesterunitedfan.model.enums.NationalityEnum;
import com.example.manchesterunitedfan.model.enums.PositionEnum;

import javax.validation.constraints.*;

public class EditPlayerBindingModel {
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

    public EditPlayerBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EditPlayerBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotBlank
    public String getLastName() {
        return lastName;
    }

    public EditPlayerBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @NotNull
    @Positive
    public Integer getSquadNumber() {
        return squadNumber;
    }

    public EditPlayerBindingModel setSquadNumber(Integer squadNumber) {
        this.squadNumber = squadNumber;
        return this;
    }


    public String getImgUrl() {
        return imgUrl;
    }

    public EditPlayerBindingModel setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    @NotNull
    public PositionEnum getPosition() {
        return position;
    }

    public EditPlayerBindingModel setPosition(PositionEnum position) {
        this.position = position;
        return this;
    }

    @NotNull
    @Min(15)
    @Max(45)
    public Integer getAge() {
        return age;
    }

    public EditPlayerBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public EditPlayerBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    @NotNull
    public NationalityEnum getNationality() {
        return nationality;
    }

    public EditPlayerBindingModel setNationality(NationalityEnum nationality) {
        this.nationality = nationality;
        return this;
    }
}

package com.example.manchesterunitedfan.model.entities;

import com.example.manchesterunitedfan.model.enums.NationalityEnum;
import com.example.manchesterunitedfan.model.enums.PositionEnum;

import javax.persistence.*;

@Entity
@Table(name = "players")
public class PlayerEntity extends BaseEntity{
    private String firstName;
    private String lastName;
    private Integer squadNumber;
    private String imgUrl;
    private PositionEnum position;
    private Integer age;
    private String description;
    private NationalityEnum nationality;

    @Column
    public String getFirstName() {
        return firstName;
    }

    public PlayerEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }
    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public PlayerEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    @Column(nullable = false, name = "squad_number", unique = true)
    public Integer getSquadNumber() {
        return squadNumber;
    }

    public PlayerEntity setSquadNumber(Integer squadNumber) {
        this.squadNumber = squadNumber;
        return this;
    }

    @Column(name = "img_url")
    public String getImgUrl() {
        return imgUrl;
    }

    public PlayerEntity setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public PositionEnum getPosition() {
        return position;
    }

    public PlayerEntity setPosition(PositionEnum position) {
        this.position = position;
        return this;
    }

    @Column(nullable = false)
    public Integer getAge() {
        return age;
    }

    public PlayerEntity setAge(Integer age) {
        this.age = age;
        return this;
    }


    @Column
    public String getDescription() {
        return description;
    }

    public PlayerEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public NationalityEnum getNationality() {
        return nationality;
    }

    public PlayerEntity setNationality(NationalityEnum nationality) {
        this.nationality = nationality;
        return this;
    }
}

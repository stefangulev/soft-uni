package com.example.manchesterunitedfan.model.view;

import com.example.manchesterunitedfan.model.enums.PositionEnum;

public class PlayerCardView {
    private Long id;
    private String firstName;
    private String lastName;
    private PositionEnum position;
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public PlayerCardView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public PlayerCardView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public PlayerCardView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public PlayerCardView setPosition(PositionEnum position) {
        this.position = position;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public PlayerCardView setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }
}

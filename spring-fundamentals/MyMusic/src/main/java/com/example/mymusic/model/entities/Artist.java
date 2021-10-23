package com.example.mymusic.model.entities;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist extends BaseEntity {
    private ArtistNameEnum name;
    private String careerInformation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public ArtistNameEnum getName() {
        return name;
    }

    public Artist setName(ArtistNameEnum name) {
        this.name = name;
        return this;
    }

    @Column(name = "career_information", columnDefinition = "TEXT")
    public String getCareerInformation() {
        return careerInformation;
    }

    public Artist setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
        return this;
    }
}

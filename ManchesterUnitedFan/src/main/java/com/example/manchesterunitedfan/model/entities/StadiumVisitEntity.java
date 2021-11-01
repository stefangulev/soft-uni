package com.example.manchesterunitedfan.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "stadium_visits")
public class StadiumVisitEntity extends BaseEntity{
    private LocalDate date;
    private Integer visitors;
    private String additionalInformation;
    private UserEntity user;

    @Column(nullable = false)
    public LocalDate getDate() {
        return date;
    }

    public StadiumVisitEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }
    @Column(nullable = false)
    public Integer getVisitors() {
        return visitors;
    }

    public StadiumVisitEntity setVisitors(Integer visitors) {
        this.visitors = visitors;
        return this;
    }


    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public StadiumVisitEntity setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public StadiumVisitEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}

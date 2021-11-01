package com.example.manchesterunitedfan.model.service;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class AddStadiumVisitServiceModel {
    private LocalDate date;
    private Integer visitors;
    private String additionalInformation;

    public LocalDate getDate() {
        return date;
    }

    public AddStadiumVisitServiceModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Integer getVisitors() {
        return visitors;
    }

    public AddStadiumVisitServiceModel setVisitors(Integer visitors) {
        this.visitors = visitors;
        return this;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public AddStadiumVisitServiceModel setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
}

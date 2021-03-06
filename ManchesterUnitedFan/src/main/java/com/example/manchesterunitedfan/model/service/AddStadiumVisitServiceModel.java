package com.example.manchesterunitedfan.model.service;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddStadiumVisitServiceModel {
    private LocalDateTime date;
    private Integer visitors;
    private String additionalInformation;

    public LocalDateTime getDate() {
        return date;
    }

    public AddStadiumVisitServiceModel setDate(LocalDateTime date) {
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

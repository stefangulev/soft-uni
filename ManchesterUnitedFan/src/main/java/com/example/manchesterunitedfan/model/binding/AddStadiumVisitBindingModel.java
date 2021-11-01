package com.example.manchesterunitedfan.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class AddStadiumVisitBindingModel {
    private LocalDate date;
    private Integer visitors;
    private String additionalInformation;

    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getDate() {
        return date;
    }

    public AddStadiumVisitBindingModel setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @NotNull
    @Positive
    public Integer getVisitors() {
        return visitors;
    }

    public AddStadiumVisitBindingModel setVisitors(Integer visitors) {
        this.visitors = visitors;
        return this;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public AddStadiumVisitBindingModel setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }
}

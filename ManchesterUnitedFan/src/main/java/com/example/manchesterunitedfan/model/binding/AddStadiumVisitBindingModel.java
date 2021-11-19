package com.example.manchesterunitedfan.model.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddStadiumVisitBindingModel {
    private LocalDateTime date;
    private Integer visitors;
    private String additionalInformation;


    @NotNull
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getDate() {
        return date;
    }

    public AddStadiumVisitBindingModel setDate(LocalDateTime date) {
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

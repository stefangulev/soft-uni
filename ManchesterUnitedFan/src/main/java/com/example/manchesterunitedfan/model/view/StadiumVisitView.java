package com.example.manchesterunitedfan.model.view;

import java.time.LocalDate;

public class StadiumVisitView {
    private LocalDate date;
    private Integer visitors;
    private String username;

    public LocalDate getDate() {
        return date;
    }

    public StadiumVisitView setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public Integer getVisitors() {
        return visitors;
    }

    public StadiumVisitView setVisitors(Integer visitors) {
        this.visitors = visitors;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public StadiumVisitView setUsername(String username) {
        this.username = username;
        return this;
    }
}

package com.example.manchesterunitedfan.model.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StadiumVisitView {

    private Long id;
    private LocalDateTime date;
    private Integer visitors;
    private String username;

    public Long getId() {
        return id;
    }

    public StadiumVisitView setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public StadiumVisitView setDate(LocalDateTime date) {
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

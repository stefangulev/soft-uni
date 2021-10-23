package com.example.andreys_exam_db.user;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.UUID;

@Component
@SessionScope
public class CurrentUser {
    private UUID id;
    private boolean isLoggedIn;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public CurrentUser setId(UUID id) {
        this.id = id;
        return this;
    }
}

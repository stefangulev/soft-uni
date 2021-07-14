package com.example.demo.models.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {
    private User User;
    private Set<Game> gameSet;

    public Order() {
        gameSet = new HashSet<>();
    }

    @ManyToOne
    public com.example.demo.models.entities.User getUser() {
        return User;
    }

    public void setUser(com.example.demo.models.entities.User user) {
        User = user;
    }

    @ManyToMany
    public Set<Game> getGameSet() {
        return gameSet;
    }

    public void setGameSet(Set<Game> gameSet) {
        this.gameSet = gameSet;
    }
}

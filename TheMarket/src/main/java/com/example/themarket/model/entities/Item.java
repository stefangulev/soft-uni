package com.example.themarket.model.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item extends BaseEntity{
    private String name;
    private User owner;
    private Contract contract;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }
    @ManyToOne(optional = false)
    public User getOwner() {
        return owner;
    }

    public Item setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    @OneToOne(mappedBy = "item")
    public Contract getContract() {
        return contract;
    }

    public Item setContract(Contract contract) {
        this.contract = contract;
        return this;
    }
}

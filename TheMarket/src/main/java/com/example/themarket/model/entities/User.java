package com.example.themarket.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {
    private String username;
    private BigDecimal accountBalance;
    private Set<Item> ownedItems;
    private Set<Contract> buyerContracts;
    private Set<Contract> sellerContracts;

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }
    @Column(name = "account_balance", nullable = false)
    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public User setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
        return this;
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    public Set<Item> getOwnedItems() {
        return ownedItems;
    }

    public User setOwnedItems(Set<Item> ownedItems) {
        this.ownedItems = ownedItems;
        return this;
    }

    @OneToMany(mappedBy = "buyer")
    public Set<Contract> getBuyerContracts() {
        return buyerContracts;
    }

    public User setBuyerContracts(Set<Contract> buyerContracts) {
        this.buyerContracts = buyerContracts;
        return this;
    }
    @OneToMany(mappedBy = "seller")
    public Set<Contract> getSellerContracts() {
        return sellerContracts;
    }

    public User setSellerContracts(Set<Contract> sellerContracts) {
        this.sellerContracts = sellerContracts;
        return this;
    }
}

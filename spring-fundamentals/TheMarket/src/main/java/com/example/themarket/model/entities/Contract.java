package com.example.themarket.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity {
    private User seller;
    private User buyer;
    private Item item;
    private BigDecimal price;
    private boolean active;

    @ManyToOne(optional = false)
    public User getSeller() {
        return seller;
    }

    public Contract setSeller(User seller) {
        this.seller = seller;
        return this;
    }
    @ManyToOne
    public User getBuyer() {
        return buyer;
    }

    public Contract setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }

    @OneToOne(optional = false)
    public Item getItem() {
        return item;
    }

    public Contract setItem(Item item) {
        this.item = item;
        return this;
    }
    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public Contract setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Contract setActive(boolean active) {
        this.active = active;
        return this;
    }
}

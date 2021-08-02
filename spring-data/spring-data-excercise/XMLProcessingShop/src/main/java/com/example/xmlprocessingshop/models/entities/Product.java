package com.example.xmlprocessingshop.models.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Product extends BaseEntity{
    private String name;
    private BigDecimal price;
    private User buyer;
    private User seller;
    private Set<Category> categories;



    public Product() {

    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
    @ManyToMany(fetch = FetchType.EAGER)
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}

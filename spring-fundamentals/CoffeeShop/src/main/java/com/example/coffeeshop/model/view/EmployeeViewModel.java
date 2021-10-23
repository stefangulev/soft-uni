package com.example.coffeeshop.model.view;

import com.example.coffeeshop.model.entities.Order;

import java.util.Set;

public class EmployeeViewModel {
    private String username;
    private Set<Order> orders;

    public String getUsername() {
        return username;
    }

    public EmployeeViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public EmployeeViewModel setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }
}

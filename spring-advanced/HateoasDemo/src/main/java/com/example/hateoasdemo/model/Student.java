package com.example.hateoasdemo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BaseEntity{
    private String name;
    private Integer age;
    private Set<Order> orders;

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public Integer getAge() {
        return age;
    }

    public Student setAge(Integer age) {
        this.age = age;
        return this;
    }

    @OneToMany(mappedBy = "student")
    public Set<Order> getOrders() {
        return orders;
    }

    public Student setOrders(Set<Order> orders) {
        this.orders = orders;
        return this;
    }
}

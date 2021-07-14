package entities.classes;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

import java.time.LocalDate;

@Entity(name = "users")
public class User {
    @Id(name = "user_id")
    private long id;
    @Column(name = "username", declaredType = "VARCHAR(100)")
    private String name;
    @Column(name = "user_age", declaredType = "INT(11)")
    private int age;
    @Column(name = "user_registration", declaredType = "DATE")
    private LocalDate registration;

    public User(String name, int age, LocalDate registration) {
        this.name = name;
        this.age = age;
        this.registration = registration;
    }

    public long getid() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getRegistration() {
        return registration;
    }

    public void setRegistration(LocalDate registration) {
        this.registration = registration;
    }
}

package com.example.pathfinder.model.view;

public class ProfileViewModel {
    private String level;
    private String fullName;
    private String username;
    private Integer age;

    public String getLevel() {
        return level;
    }

    public ProfileViewModel setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public ProfileViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ProfileViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public ProfileViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }
}

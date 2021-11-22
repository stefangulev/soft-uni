package com.example.pathfinder.model.binding;

import javax.validation.constraints.*;

public class UserRegisterBindingModel {
    private String username;
    private String fullName;
    private String email;
    private int age;
    private String password;
    private String confirmPassword;

    @NotBlank
    @Size(min = 3)
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotBlank
    @Size(min = 3)
    public String getFullName() {
        return fullName;
    }

    public UserRegisterBindingModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @NotBlank
    @Email
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @NotNull
    @Positive
    public int getAge() {
        return age;
    }

    public UserRegisterBindingModel setAge(int age) {
        this.age = age;
        return this;
    }
    @NotBlank
    @Size(min = 5)
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    @NotBlank
    @Size(min = 5)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}

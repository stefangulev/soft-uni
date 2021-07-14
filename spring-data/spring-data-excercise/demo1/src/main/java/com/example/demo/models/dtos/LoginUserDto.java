package com.example.demo.models.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class LoginUserDto {
    private String email;
    private String password;

    public LoginUserDto() {

    }

    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Email(message = "Incorrect email.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = "[A-Za-z0-9]{6,}", message = "Invalid password!")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

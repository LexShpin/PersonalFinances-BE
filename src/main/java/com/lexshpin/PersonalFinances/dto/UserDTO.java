package com.lexshpin.PersonalFinances.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @Column(name = "name")
    @NotEmpty(message = "Name must not be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters long")
    private String name;

    @Column(name = "email")
    @NotEmpty(message = "Please enter your email")
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Please enter the password")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

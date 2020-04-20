package com.cognizant.noc.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "City is mandatory")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "password")
    private String password;

    public Admin(long id,  String city,  String password) {
        this.id = id;
        this.city = city;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.thiagoleite.GastroHubSolo.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Date lastUpdatedAt;
    private String address;

    public User() {}

    public User(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = "USER";
        this.lastUpdatedAt = new Date();
    }
}


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
    private String address;
    private String email;
    private String password;
    private Date lastUpdatedAt;

    public User(Long id, String name, String address, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
    }
}


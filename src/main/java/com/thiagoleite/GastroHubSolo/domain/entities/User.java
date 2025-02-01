package com.thiagoleite.GastroHubSolo.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Date;

@EqualsAndHashCode
@Getter
public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private Date lastUpdatedAt;
    private String address;

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

}


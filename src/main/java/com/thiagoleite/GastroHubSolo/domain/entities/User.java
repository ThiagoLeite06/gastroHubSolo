package com.thiagoleite.GastroHubSolo.domain.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
public class User {
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    private String address;
    private String email;
    private String password;
    private Date lastUpdatedAt;
    private String role;

    public User(Long id, String name, String address, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}


package com.thiagoleite.GastroHubSolo.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
public class UserDomain {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private Date lastUpdatedAt;
    private String address;
    private UserTypeDomain userTypeDomain;

    public UserDomain() {}

    public UserDomain(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = "USER";
        this.lastUpdatedAt = new Date();
    }
}


package com.thiagoleite.GastroHubSolo.domain.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
public class UserType {
    private Long id;
    private String name;
    private Date createdAt;
    private Date lastUpdatedAt;

    public UserType() {
        this.createdAt = new Date();
        this.lastUpdatedAt = new Date();
    }

    public UserType(String name) {
        this();
        this.name = name;
    }
}
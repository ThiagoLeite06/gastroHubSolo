package com.thiagoleite.GastroHubSolo.core.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@EqualsAndHashCode
@Getter
@Setter
public class UserTypeDomain {
    private Long id;
    private String name;
    private Date createdAt;
    private Date lastUpdatedAt;

    public UserTypeDomain() {
        this.createdAt = new Date();
        this.lastUpdatedAt = new Date();
    }

    public UserTypeDomain(String name) {
        this();
        this.name = name;
    }
}
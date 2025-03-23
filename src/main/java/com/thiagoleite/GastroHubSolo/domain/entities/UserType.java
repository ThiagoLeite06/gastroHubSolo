package com.thiagoleite.GastroHubSolo.domain.entities;


import java.time.LocalDateTime;


public class UserType {
    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public UserType() {
        this.createdAt = LocalDateTime.now();
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public UserType(String name) {
        this();
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
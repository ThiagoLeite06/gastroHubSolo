package com.thiagoleite.GastroHubSolo.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private LocalDateTime lastUpdatedAt;
    private String address;
    private UserType userType;

    public User() {}

    public User(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = "USER";
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public LocalDateTime setLastUpdatedAt() {
        return LocalDateTime.now();
    }

    public String getAddress() {
        return address;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setName(String name) {
        this.name = name;
        updateLastUpdatedAt();
    }

    public void setEmail(String email) {
        this.email = email;
        updateLastUpdatedAt();
    }

    public void setPassword(String password) {
        this.password = password;
        updateLastUpdatedAt();
    }

    public void setRole(String role) {
        this.role = role;
        updateLastUpdatedAt();
    }

    public void setAddress(String address) {
        this.address = address;
        updateLastUpdatedAt();
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
        updateLastUpdatedAt();
    }

    private void updateLastUpdatedAt() {
        this.lastUpdatedAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", address='" + address + '\'' +
                ", userType=" + userType +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}
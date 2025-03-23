package com.thiagoleite.GastroHubSolo.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private String cuisineType;
    private String operatingHours;
    private User owner;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public Restaurant() {
        this.createdAt = LocalDateTime.now();
        this.lastUpdatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Restaurant that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getAddress(), that.getAddress()) && Objects.equals(getCuisineType(), that.getCuisineType()) && Objects.equals(getOperatingHours(), that.getOperatingHours()) && Objects.equals(getOwner(), that.getOwner()) && Objects.equals(getCreatedAt(), that.getCreatedAt()) && Objects.equals(getLastUpdatedAt(), that.getLastUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getCuisineType(), getOperatingHours(), getOwner(), getCreatedAt(), getLastUpdatedAt());
    }
}

package com.thiagoleite.GastroHubSolo.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class MenuItem {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean availableOnlyInRestaurant;
    private String photoPath;
    private Restaurant restaurant;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;

    public MenuItem() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isAvailableOnlyInRestaurant() {
        return availableOnlyInRestaurant;
    }

    public void setAvailableOnlyInRestaurant(boolean availableOnlyInRestaurant) {
        this.availableOnlyInRestaurant = availableOnlyInRestaurant;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
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
        if (!(o instanceof MenuItem menuItem)) return false;
        return isAvailableOnlyInRestaurant() == menuItem.isAvailableOnlyInRestaurant() && Objects.equals(getId(), menuItem.getId()) && Objects.equals(getName(), menuItem.getName()) && Objects.equals(getDescription(), menuItem.getDescription()) && Objects.equals(getPrice(), menuItem.getPrice()) && Objects.equals(getPhotoPath(), menuItem.getPhotoPath()) && Objects.equals(getRestaurant(), menuItem.getRestaurant()) && Objects.equals(getCreatedAt(), menuItem.getCreatedAt()) && Objects.equals(getLastUpdatedAt(), menuItem.getLastUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), isAvailableOnlyInRestaurant(), getPhotoPath(), getRestaurant(), getCreatedAt(), getLastUpdatedAt());
    }
}
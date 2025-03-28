package com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "menu_items")
public class MenuItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "available_only_in_restaurant")
    private boolean availableOnlyInRestaurant;

    @Column(name = "photo_path")
    private String photoPath;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "last_updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastUpdatedAt;

    public MenuItemEntity() {
        this.createdAt = LocalDateTime.now();
        this.lastUpdatedAt = LocalDateTime.now();
    }
}
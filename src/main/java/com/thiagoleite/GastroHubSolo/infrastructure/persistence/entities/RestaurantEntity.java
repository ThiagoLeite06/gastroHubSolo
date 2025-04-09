//package com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities;
//
//import jakarta.persistence.*;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//@Table(name = "restaurants")
//public class RestaurantEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false, length = 100)
//    private String name;
//
//    @Column(nullable = false, length = 200)
//    private String address;
//
//    @Column(name = "cuisine_type", nullable = false, length = 50)
//    private String cuisineType;
//
//    @Column(name = "operating_hours", nullable = false, length = 100)
//    private String operatingHours;
//
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "owner_id", nullable = false)
//    private UserEntity owner;
//
//    @Column(name = "created_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime createdAt;
//
//    @Column(name = "last_updated_at")
//    @Temporal(TemporalType.TIMESTAMP)
//    private LocalDateTime lastUpdatedAt;
//
//    public RestaurantEntity() {
//        this.createdAt =LocalDateTime.now();
//        this.lastUpdatedAt = LocalDateTime.now();
//    }
//}
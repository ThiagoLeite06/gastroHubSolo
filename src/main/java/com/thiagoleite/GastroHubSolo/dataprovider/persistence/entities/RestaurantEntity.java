package com.thiagoleite.GastroHubSolo.dataprovider.persistence.entities;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;
import com.thiagoleite.GastroHubSolo.core.domain.enums.KitchenTypes;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "restaurant")
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 200)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KitchenTypes kitchenType;

    @Column(length = 100)
    private String openingHours;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserDomain owner;
}

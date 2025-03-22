package com.thiagoleite.GastroHubSolo.core.domain;

import com.thiagoleite.GastroHubSolo.core.domain.enums.KitchenTypes;
import lombok.Getter;

@Getter
public class RestaurantDomain {
    private Long id;
    private String name;
    private String adress;
    private KitchenTypes kitchenType;
    private String openingHours;
    private UserDomain owner;

    public RestaurantDomain() {}

    public RestaurantDomain(Long id, String name, KitchenTypes kitchenType, String adress, String openingHours, UserDomain owner) {
        this.id = id;
        this.name = name;
        this.kitchenType = kitchenType;
        this.adress = adress;
        this.openingHours = openingHours;
        this.owner = owner;
    }
}

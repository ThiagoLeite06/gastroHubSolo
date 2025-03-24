package com.thiagoleite.GastroHubSolo.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class MenuItemOutput {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean availableOnlyInRestaurant;
    private String photoPath;
    private RestaurantOutput restaurant;
    private Date createdAt;
    private Date lastUpdatedAt;
}
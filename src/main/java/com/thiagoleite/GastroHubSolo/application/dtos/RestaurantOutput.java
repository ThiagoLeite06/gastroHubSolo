package com.thiagoleite.GastroHubSolo.application.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RestaurantOutput {
    private Long id;
    private String name;
    private String address;
    private String cuisineType;
    private String operatingHours;
    private UserOutput owner;
    private Date createdAt;
    private Date lastUpdatedAt;
}
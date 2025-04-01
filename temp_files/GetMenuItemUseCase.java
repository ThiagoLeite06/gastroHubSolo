package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.MenuItemOutput;

import java.util.List;

public interface GetMenuItemUseCase {
    MenuItemOutput getById(Long id);
    List<MenuItemOutput> getAll();
    List<MenuItemOutput> getByRestaurantId(Long restaurantId);
}

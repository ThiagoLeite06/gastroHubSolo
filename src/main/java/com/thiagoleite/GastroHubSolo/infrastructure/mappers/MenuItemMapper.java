//package com.thiagoleite.GastroHubSolo.infrastructure.mappers;
//
//import com.thiagoleite.GastroHubSolo.application.dtos.CreateMenuItemInput;
//import com.thiagoleite.GastroHubSolo.application.dtos.UpdateMenuItemInput;
//import com.thiagoleite.GastroHubSolo.application.dtos.MenuItemOutput;
//import com.thiagoleite.GastroHubSolo.domain.entities.MenuItem;
//import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.MenuItemEntity;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MenuItemMapper {
//    private final ModelMapper modelMapper;
//    private final RestaurantMapper restaurantMapper;
//
//    public MenuItemMapper(ModelMapper modelMapper, RestaurantMapper restaurantMapper) {
//        this.modelMapper = modelMapper;
//        this.restaurantMapper = restaurantMapper;
//    }
//
//    // DTO -> Domain
//    public MenuItem toDomain(CreateMenuItemInput input) {
//        return modelMapper.map(input, MenuItem.class);
//    }
//
//    public MenuItem toDomain(UpdateMenuItemInput input) {
//        return modelMapper.map(input, MenuItem.class);
//    }
//
//    // Domain -> DTO
//    public MenuItemOutput toOutput(MenuItem menuItem) {
//        MenuItemOutput output = modelMapper.map(menuItem, MenuItemOutput.class);
//
//        if (menuItem.getRestaurant() != null) {
//            output.setRestaurant(restaurantMapper.toOutput(menuItem.getRestaurant()));
//        }
//
//        return output;
//    }
//
//    // Domain -> Entity
//    public MenuItemEntity toEntity(MenuItem menuItem) {
//        MenuItemEntity entity = modelMapper.map(menuItem, MenuItemEntity.class);
//
//        if (menuItem.getRestaurant() != null) {
//            RestaurantEntity restaurantEntity = new RestaurantEntity();
//            restaurantEntity.setId(menuItem.getRestaurant().getId());
//            entity.setRestaurant(restaurantEntity);
//        }
//
//        return entity;
//    }
//
//    // Entity -> Domain
//    public MenuItem toDomain(MenuItemEntity entity) {
//        MenuItem menuItem = modelMapper.map(entity, MenuItem.class);
//
//        if (entity.getRestaurant() != null) {
//            menuItem.setRestaurant(restaurantMapper.toDomain(entity.getRestaurant()));
//        }
//
//        return menuItem;
//    }
//}
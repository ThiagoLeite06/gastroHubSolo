//package com.thiagoleite.GastroHubSolo.infrastructure.mappers;
//
//import com.thiagoleite.GastroHubSolo.application.dtos.CreateRestaurantInput;
//import com.thiagoleite.GastroHubSolo.application.dtos.UpdateRestaurantInput;
//import com.thiagoleite.GastroHubSolo.application.dtos.RestaurantOutput;
//import com.thiagoleite.GastroHubSolo.domain.entities.Restaurant;
//import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.RestaurantEntity;
//
//import com.thiagoleite.GastroHubSolo.presentation.mappers.UserMapper;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RestaurantMapper {
//    private final ModelMapper modelMapper;
//    private final UserMapper userMapper;
//
//    public RestaurantMapper(ModelMapper modelMapper, UserMapper userMapper) {
//        this.modelMapper = modelMapper;
//        this.userMapper = userMapper;
//    }
//
//    // DTO -> Domain
//    public Restaurant toDomain(CreateRestaurantInput input) {
//        return modelMapper.map(input, Restaurant.class);
//    }
//
//    public Restaurant toDomain(UpdateRestaurantInput input) {
//        return modelMapper.map(input, Restaurant.class);
//    }
//
//    // Domain -> DTO
//    public RestaurantOutput toOutput(Restaurant restaurant) {
//        RestaurantOutput output = modelMapper.map(restaurant, RestaurantOutput.class);
//
//        if (restaurant.getOwner() != null) {
//            output.setOwner(userMapper.toOutput(restaurant.getOwner()));
//        }
//
//        return output;
//    }
//
//    // Domain -> Entity
//    public RestaurantEntity toEntity(Restaurant restaurant) {
//        RestaurantEntity entity = modelMapper.map(restaurant, RestaurantEntity.class);
//
//        if (restaurant.getOwner() != null) {
//            UserEntity userEntity = new UserEntity();
//            userEntity.setId(restaurant.getOwner().getId());
//            entity.setOwner(userEntity);
//        }
//
//        return entity;
//    }
//
//    // Entity -> Domain
//    public Restaurant toDomain(RestaurantEntity entity) {
//        Restaurant restaurant = modelMapper.map(entity, Restaurant.class);
//
//        if (entity.getOwner() != null) {
//            restaurant.setOwner(userMapper.toUser(entity.getOwner()));
//        }
//
//        return restaurant;
//    }
//}
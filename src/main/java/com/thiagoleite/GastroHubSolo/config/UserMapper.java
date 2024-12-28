package com.thiagoleite.GastroHubSolo.config;

import com.thiagoleite.GastroHubSolo.dto.UserResponseDto;
import com.thiagoleite.GastroHubSolo.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
    User toEntity(UserResponseDto userResponseDto);
}

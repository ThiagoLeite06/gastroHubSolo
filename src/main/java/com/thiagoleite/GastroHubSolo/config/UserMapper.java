package com.thiagoleite.GastroHubSolo.config;

import com.thiagoleite.GastroHubSolo.dto.UserResponseDto;
import com.thiagoleite.GastroHubSolo.dto.UserRequestDto;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
    User toEntity(UserRequestDto userRequestDto);
}

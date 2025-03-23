package com.thiagoleite.GastroHubSolo.infrastructure.mappers;

import com.thiagoleite.GastroHubSolo.application.dtos.UserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserOutput;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserEntity;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // DTO -> Domain
    public User toUser(UserInput input) {
        return modelMapper.map(input, User.class);
    }

    // Domain -> DTO
    public UserOutput toOutput(User user) {
        return modelMapper.map(user, UserOutput.class);
    }

    // Domain -> Entity
    public UserEntity toEntity(User user) {
        UserEntity entity = modelMapper.map(user, UserEntity.class);

        if (user.getUserType() != null) {
            UserTypeEntity userTypeEntity = new UserTypeEntity();
            userTypeEntity.setId(user.getUserType().getId());
            entity.setUserType(userTypeEntity);
        }

        return entity;
    }

    // Entity -> Domain
    public User toUser(UserEntity entity) {
        return modelMapper.map(entity, User.class);
    }
}

package com.thiagoleite.GastroHubSolo.infrastructure.mappers;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateUserDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.UserResponseDTO;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;
    private final UserTypeRepository userTypeRepository;


    @Autowired
    public UserMapper(ModelMapper modelMapper, UserTypeRepository userTypeRepository) {
        this.modelMapper = modelMapper;
        this.userTypeRepository = userTypeRepository;
    }

    // DTO -> Domain
    public User toUser(CreateUserDTO dto) {
        User user = modelMapper.map(dto, User.class);

        if (dto.getUserTypeId() != null) {
            UserType userType = userTypeRepository.findById(dto.getUserTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));
            user.setUserType(userType);
        }

        return user;
    }

    // Domain -> DTO
    public UserResponseDTO toResponseDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    // Domain -> Entity
    public UserEntity toEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }

    // Entity -> Domain
    public User toUser(UserEntity entity) {
        return modelMapper.map(entity, User.class);
    }
}

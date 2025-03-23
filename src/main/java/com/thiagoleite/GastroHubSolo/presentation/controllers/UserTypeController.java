package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateUserTypeDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.UpdateUserTypeDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.UserTypeOutput;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.usecases.CreateUserTypeUseCase;
import com.thiagoleite.GastroHubSolo.domain.usecases.DeleteUserTypeUseCase;
import com.thiagoleite.GastroHubSolo.domain.usecases.GetUserTypeUseCase;
import com.thiagoleite.GastroHubSolo.domain.usecases.UpdateUserTypeUseCase;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserTypeMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-types")
public class UserTypeController {

    @Autowired
    private CreateUserTypeUseCase createUserTypeUseCase;

    @Autowired
    private UpdateUserTypeUseCase updateUserTypeUseCase;

    @Autowired
    private DeleteUserTypeUseCase deleteUserTypeUseCase;

    @Autowired
    private GetUserTypeUseCase getUserTypeUseCase;

    @Autowired
    private UserTypeMapper userTypeMapper;

    @PostMapping
    public ResponseEntity<UserTypeOutput> createUserType(@Valid @RequestBody CreateUserTypeDTO createUserTypeDTO) {
        // Converter DTO para domínio
        UserType userType = new UserType();
        userType.setName(createUserTypeDTO.getName());

        // Executar caso de uso
        UserType createdUserType = createUserTypeUseCase.execute(userType);

        // Converter resultado para DTO de resposta
        UserTypeOutput responseDTO = userTypeMapper.toResponseDTO(createdUserType);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTypeOutput> updateUserType(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserTypeDTO updateUserTypeDTO) {

        // Converter DTO para domínio
        UserType userType = new UserType();
        userType.setName(updateUserTypeDTO.getName());

        // Executar caso de uso
        UserType updatedUserType = updateUserTypeUseCase.execute(id, userType);

        // Converter resultado para DTO de resposta
        UserTypeOutput responseDTO = userTypeMapper.toResponseDTO(updatedUserType);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable Long id) {
        deleteUserTypeUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTypeOutput> getUserTypeById(@PathVariable Long id) {
        UserType userType = getUserTypeUseCase.getById(id);
        UserTypeOutput responseDTO = userTypeMapper.toResponseDTO(userType);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserTypeOutput>> getAllUserTypes() {
        List<UserType> userTypes = getUserTypeUseCase.getAll();
        List<UserTypeOutput> userTypeOutputs = userTypes.stream()
                .map(userTypeMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userTypeOutputs);
    }
}
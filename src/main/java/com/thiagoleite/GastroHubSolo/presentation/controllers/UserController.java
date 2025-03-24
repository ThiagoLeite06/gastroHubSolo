package com.thiagoleite.GastroHubSolo.presentation.controllers;

import com.thiagoleite.GastroHubSolo.application.dtos.ChangePasswordInput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.UpdateUserInput;
import com.thiagoleite.GastroHubSolo.domain.usecases.*;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final UserMapper userMapper;

    public UserController(
            CreateUserUseCase createUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase,
            GetUserUseCase getUserUseCase,
            ChangePasswordUseCase changePasswordUseCase,
            UserMapper userMapper) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.getUserUseCase = getUserUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserOutput> createUser(@RequestBody UserInput userInput) {
        UserOutput userOutput = createUserUseCase.execute(userInput);
        return ResponseEntity.ok(userOutput);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserOutput> updateUser(@PathVariable Long id,
            @Valid @RequestBody UpdateUserInput updateUserInput) {
        UserOutput output = updateUserUseCase.execute(id, updateUserInput);
        return ResponseEntity.ok(output);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserOutput> getUserById(@PathVariable Long id) {
        UserOutput output = getUserUseCase.getById(id);
        return ResponseEntity.ok(output);
    }

    @GetMapping
    public ResponseEntity<List<UserOutput>> getAllUsers() {
        List<UserOutput> outputs = getUserUseCase.getAll();
        return ResponseEntity.ok(outputs);
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @Valid @RequestBody ChangePasswordInput changePasswordInput) {
        changePasswordUseCase.execute(
                id,
                changePasswordInput.getCurrentPassword(),
                changePasswordInput.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
